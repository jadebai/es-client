package com.baiyu.es.client.search.builder;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.query.QueryFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

/**
 * @author baiyu
 * @description: AbstractSearchBuilder
 * @date: 2018/11/6
 */
@Data
@AllArgsConstructor
public abstract class AbstractSearchBuilder implements SearchBuilder{

    private volatile SearchRequestBuilder searchRequestBuilder;
    private volatile BoolQueryBuilder boolQueryBuilder;
    private SearchTypeParam searchTypeParam;


    @Override
    public AbstractSearchBuilder searchRequestBuilder() {
        setQuery(boolQueryBuilder, QueryFactory.queryBuilderList(searchTypeParam));
        return this;
    }

    abstract void setQuery(BoolQueryBuilder boolQueryBuilder, List<QueryBuilder> queryBuilderList);

    public QueryBuilder scoreWarp(QueryBuilder queryBuilder){
        if (!searchTypeParam.isScore()){
            queryBuilder = QueryBuilders.constantScoreQuery(queryBuilder);
        }
        return queryBuilder;
    }

}
