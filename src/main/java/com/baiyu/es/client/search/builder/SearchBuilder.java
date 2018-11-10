package com.baiyu.es.client.search.builder;

/**
 * @author baiyu
 * @description: SearchBuilder 初始化 赋值 SearchRequestBuilder
 * @date: 2018/11/6
 */
public interface SearchBuilder {

    /**
     * 返回 SearchRequestBuilder
     * @return
     */
    AbstractSearchBuilder searchRequestBuilder();
//
//    /**
//     * 返回 QueryBuilder
//     * @return
//     */
//    QueryBuilder searchQueryBuilder();
}
