package com.baiyu.es.client.handler;

import com.baiyu.es.client.model.EsModel;
import com.baiyu.es.client.model.SearchResp;
import com.baiyu.es.client.search.bean.SearchParam;
import com.baiyu.es.client.search.builder.SearchRequestBuilderFactory;
import org.elasticsearch.action.search.SearchRequestBuilder;

/**
 * @author baiyu
 * @description: AbstractEsSearchHandler
 * @date: 2018/10/21
 */
public abstract class AbstractEsSearchHandler<T extends EsModel> extends AbstrateEsDataHandler<T>{

    /**
     * 根据条件搜索数据
     *
     * @param param
     * @return
     */
    @Override
    public final SearchResp search(SearchParam param) {
        return doSearch(getSearchRequestBuilder(),param);
    }

    private SearchResp doSearch(SearchRequestBuilder searchRequestBuilder, SearchParam param) {
        return convertResponse(SearchRequestBuilderFactory.builder(searchRequestBuilder,param).execute().actionGet());
    }
}
