package com.baiyu.es.client.search.builder;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: PostFilterSearchBuilder
 * @date: 2018/11/6
 */
public class PostFilterSearchBuilder extends AbstractSearchBuilder {

    public PostFilterSearchBuilder(SearchRequestBuilder searchRequestBuilder, BoolQueryBuilder boolQueryBuilder, SearchTypeParam searchTypeParam) {
        super(searchRequestBuilder, boolQueryBuilder, searchTypeParam);
    }

    @Override
    void setQuery(BoolQueryBuilder boolQueryBuilder,List<QueryBuilder> queryBuilderList) {
        if (CollectionUtils.isEmpty(queryBuilderList)){
            return;
        }
        for (QueryBuilder queryBuilder : queryBuilderList){
            getSearchRequestBuilder().setPostFilter(scoreWarp(queryBuilder));
        }
    }
}
