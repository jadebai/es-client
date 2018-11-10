package com.baiyu.es.client.search.builder;

import com.baiyu.es.client.search.agg.AggBuilder;
import com.baiyu.es.client.search.bean.AggParam;
import com.baiyu.es.client.search.bean.SearchParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.bean.SortParam;
import com.baiyu.es.client.search.enums.SearchTypeEnum;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author baiyu
 * @description: SearchRequestBuilderFactory  设置查询条件
 * @date: 2018/11/6
 */
public class SearchRequestBuilderFactory {

    public static SearchRequestBuilder builder(SearchRequestBuilder searchRequestBuilder, SearchParam param) {
        if (null == param) {
            return searchRequestBuilder;
        }
        addQuery(searchRequestBuilder,param);
        addAgg(searchRequestBuilder,param);
        addSort(searchRequestBuilder,param);
        page(searchRequestBuilder,param);
        return searchRequestBuilder;
    }

    private static void addQuery(SearchRequestBuilder searchRequestBuilder, SearchParam param){
        Map<SearchTypeEnum, SearchTypeParam> searchType = param.getSearchType();
        if (null != searchType && !searchType.isEmpty()) {
            AbstractSearchBuilder searchBuilder = searchBuilder(searchRequestBuilder,searchType);
            searchRequestBuilder = searchBuilder.getSearchRequestBuilder();
            searchRequestBuilder.setQuery(searchBuilder.getBoolQueryBuilder());
        }
    }

    public static AbstractSearchBuilder searchBuilder(SearchRequestBuilder searchRequestBuilder, Map<SearchTypeEnum, SearchTypeParam> searchType){
        Iterator<Map.Entry<SearchTypeEnum, SearchTypeParam>> iter = searchType.entrySet().iterator();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        AbstractSearchBuilder searchBuilder = null;
        while (iter.hasNext()) {
            Map.Entry<SearchTypeEnum, SearchTypeParam> entry = iter.next();
            searchBuilder = builderDetail(searchRequestBuilder, boolQueryBuilder, entry);
            if (null == searchBuilder){
                continue;
            }
            searchRequestBuilder = searchBuilder.getSearchRequestBuilder();
            boolQueryBuilder = searchBuilder.getBoolQueryBuilder();
        }
        return searchBuilder;
    }

    private static AbstractSearchBuilder builderDetail(SearchRequestBuilder searchRequestBuilder, BoolQueryBuilder boolQueryBuilder, Map.Entry<SearchTypeEnum, SearchTypeParam> entry) {
        switch (entry.getKey()) {
            case MUST:
               return new MustSearchBuilder(searchRequestBuilder,boolQueryBuilder,entry.getValue()).searchRequestBuilder();
            case MUST_NOT:
               return new MustNotSearchBuilder(searchRequestBuilder,boolQueryBuilder,entry.getValue()).searchRequestBuilder();
            case SHOULD:
               return new ShouldSearchBuilder(searchRequestBuilder,boolQueryBuilder,entry.getValue()).searchRequestBuilder();
            case POST_FILTER:
               return new PostFilterSearchBuilder(searchRequestBuilder,boolQueryBuilder,entry.getValue()).searchRequestBuilder();
            default: return null;
        }
    }

    private static void addAgg(SearchRequestBuilder searchRequestBuilder, SearchParam param) {
        if(CollectionUtils.isEmpty(param.getAggParamList())){
            return;
        }
        for (AggParam aggParam : param.getAggParamList()){
            searchRequestBuilder.addAggregation(new AggBuilder(aggParam).builder());
        }
    }

    private static void page(SearchRequestBuilder searchRequestBuilder, SearchParam param) {
        searchRequestBuilder.setFrom((param.getPageIndex() - 1) * param.getPageSize()).setSize(param.getPageSize());
    }

    private static void addSort(SearchRequestBuilder searchRequestBuilder, SearchParam param){
        if(CollectionUtils.isEmpty(param.getSortParamList())){
            return;
        }
        for (SortParam sortParam : param.getSortParamList()){
            searchRequestBuilder.addSort(sortParam.getFiledName(),sortParam.getSortOrder());
        }
    }

}
