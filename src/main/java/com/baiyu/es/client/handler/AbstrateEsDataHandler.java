package com.baiyu.es.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyu.es.client.exception.EsErrorCode;
import com.baiyu.es.client.exception.EsException;
import com.baiyu.es.client.model.EsModel;
import com.baiyu.es.client.model.SearchResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author baiyu
 * @description: AbstrateEsDataHandler  对外提供的 ES数据操作抽象类  可继承实现
 * @date: 2018/10/21
 */
@SuppressWarnings("unchecked")
@Slf4j
public abstract class AbstrateEsDataHandler<T extends EsModel> extends AbstractEsHandler implements IEsDataHandler<T>, IEsModelHandler<T>, InitializingBean {

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public final void afterPropertiesSet() throws Exception {
        EsSearchHandlerFactory.addHandler(getClassType(), this);
    }

    /**
     * 获取泛型的真实class
     */
    private Class<T> getClassType() {
        Type superClass = getClass().getGenericSuperclass();
        return (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    /**
     * 添加或者修改单条数据
     *
     * @param model
     * @return
     */
    @Override
    public final T upset(T model) {
        return upsetList(Lists.<T>newArrayList(model)).get(0);
    }

    /**
     * 批量添加或者修改单条数据
     *
     * @param models
     * @return
     */
    @Override
    public final List<T> upsetList(List<T> models) {
        checkArg(getIndexName(),getTypeName());
        Preconditions.checkNotNull(models, "Args models [{}] cannot be null", models);
        BulkRequestBuilder bulkRequestBuilder = getBulkRequestBuilder(models);
        BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            throw new EsException(EsErrorCode.ES_UPSET_ERROR.getErrCode(), bulkResponse.buildFailureMessage());
        }
        return models;
    }

    /**
     * 根据ID获取单条数据
     *
     * @param id
     * @return
     */
    @Override
    public final T getDataById(String id) {
        checkArg(getIndexName(),getTypeName());
        Preconditions.checkNotNull(id, "Args id [{}] cannot be null", id);
        GetResponse getResponse = getClient().prepareGet(getIndexName(),getTypeName(), id).get();
        if (getResponse == null || getResponse.isSourceEmpty()) {
            return null;
        }
        T t = JSON.parseObject(getResponse.getSourceAsString(), getClassType());
        return t;
    }

    /**
     * 根据IDS获取批量数据
     *
     * @param ids
     * @return
     */
    @Override
    public final List<T> getDatasByIds(List<String> ids) {
        Preconditions.checkNotNull(ids, "Args ids [{}] cannot be null", ids);
        SearchResponse searchResponse = getSearchRequestBuilder()
                .setQuery(QueryBuilders.termsQuery("id", ids))
                .execute()
                .actionGet();
        return convert(searchResponse);
    }

    /**
     * 根据ID 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public final boolean deleteById(String id) {
        checkArg(getIndexName(),getTypeName());
        Preconditions.checkNotNull(id, "Args id [{}] cannot be null", id);
        DeleteResponse deleteResponse = getClient().prepareDelete(getIndexName(),getTypeName(), id).get();
        if (deleteResponse == null) {
            return false;
        }
        return deleteResponse.isFound();
    }

    /**
     * 根据IDS  批量删除数据
     *
     * @param ids
     * @return
     */
    @Override
    public final boolean deleteByIds(List<String> ids) {
        BulkRequestBuilder bulkRequest = getClient().prepareBulk();
        for (String id : ids){
            bulkRequest.add(getClient().prepareDelete(getIndexName(),getTypeName(), id));
        }
        // 数量大，避免超时
        bulkRequest.setRefresh(true);
        log.info("delete by ids data:{},ids:{}", JSON.toJSONString(bulkRequest),JSON.toJSONString(ids));
        BulkResponse bulkResponse = bulkRequest.get();
        if(null != bulkResponse && bulkResponse.hasFailures()){
            log.info("bulk delete by ids error ids:{},error:{}",JSON.toJSONString(ids),bulkResponse.buildFailureMessage());
            return false;
        }
        return true;
    }

    /**
     * 查询结果数据转换
     * @param response
     * @return
     */
    protected final SearchResp convertResponse(SearchResponse response) {
        SearchResp<T> searchResp = new SearchResp<T>();
        searchResp.setAggregations(response.getAggregations());
        searchResp.setTotalSize(response.getHits().getTotalHits());
        searchResp.setEsModelList(convert(response));
        return searchResp;
    }


    /**
     * 查询结果数据转换
     * @param response
     * @return
     */
    protected final List<T> convert(SearchResponse response) {
        List<T> resultList = new ArrayList<>();
        String json = "";
        if (null != response.getHits() && response.getHits().getTotalHits() > 0L) {
            try {
                for (SearchHit hit : response.getHits()) {
                    json = hit.getSourceAsString();
                    log.info("Converting json:[{}] to clazz:[{}]", hit.getSourceAsString(), getClassType());
                    resultList.add(JSONObject.parseObject(hit.getSourceAsString(), getClassType()));
                }
            } catch (Exception e) {
                log.error("Converting json err:[{}] to clazz:[{}]", json, getClassType());
                throw new EsException(EsErrorCode.ES_JSON_ERROR);
            }
        }
        return resultList;
    }


    /**
     *  获取查询 SearchBuilder
     * @return
     */
    protected final SearchRequestBuilder getSearchRequestBuilder() {
        checkArg(getIndexName(),getTypeName());
        return getClient().prepareSearch(getIndexName()).setTypes(getTypeName())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setExplain(false);
    }


    /**
     * 获取批量操作 BulkRequestBuilder
     * @param models
     * @return
     */
    protected final BulkRequestBuilder getBulkRequestBuilder(List<T> models) {
        checkArg(getIndexName(),getTypeName());
        models = handlerList(models);
        BulkRequestBuilder bulkRequestBuilder = getClient().prepareBulk();
        for (T model : models) {
            try {
                UpdateRequestBuilder updateRequestBuilder = getClient().prepareUpdate(getIndexName(),getTypeName(), model.getId())
                        .setDoc(MAPPER.writeValueAsBytes(model))
                        .setDocAsUpsert(true);
                if (!StringUtils.isEmpty(model.getParentId())){
                    updateRequestBuilder.setParent(model.getParentId());
                }
                bulkRequestBuilder.add(updateRequestBuilder);
            } catch (JsonProcessingException e) {
                throw new EsException(EsErrorCode.ES_JSON_ERROR);
            }
        }
        // 数量大，避免超时
        bulkRequestBuilder.setRefresh(true);
        return bulkRequestBuilder;
    }

    /**
     * 处理批量数据
     *
     * @param models
     */
    @Override
    public final List<T> handlerList(List<T> models) {
        List<T> existList = getDatasByIds(Lists.transform(models, new Function<T, String>() {
            @Override
            public String apply(T model) {
                return model.getId();
            }
        }));
        Map<String,T> existMap = Maps.uniqueIndex(existList, new Function<T, String>() {
            @Override
            public String apply(T model) {
                return model.getId();
            }
        });
        for (T model : models) {
            doHandlerOne(model,existMap.get(model.getId()));
        }
        return models;
    }

    private void checkArg(String index, String type){
        Preconditions.checkNotNull(index, "Args index [{}] cannot be null", index);
        Preconditions.checkNotNull(index, "Args index-type [{}] cannot be null", type);
    }

    private void doHandlerOne(T model, T oldModel){
        checkModel(model);
        handler(model, oldModel);
    }


    /**
     * check 参数
     *
     * @param model
     */
    @Override
    public void checkModel(T model) {

    }

    /**
     * 处理单个数据
     *
     * @param model
     * @param oldModel
     */
    @Override
    public T handler(T model, T oldModel) {
        return model;
    }
}
