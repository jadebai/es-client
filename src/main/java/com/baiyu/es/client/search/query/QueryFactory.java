package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baiyu
 * @description: QueryFactory
 * @date: 2018/11/8
 */
@SuppressWarnings("unchecked")
public class QueryFactory {
    public static final ConcurrentHashMap<QueryTypeEnum,Query> QUERY_MAP = new ConcurrentHashMap<>();

    public static void addQuery(QueryTypeEnum queryTypeEnum,Query query){
        QUERY_MAP.put(queryTypeEnum,query);
    }

    public static Query instance(QueryTypeEnum queryTypeEnum){
        return QUERY_MAP.get(queryTypeEnum);
    }

    public static List<QueryBuilder> queryBuilderList(SearchTypeParam searchTypeParam){
        if (QUERY_MAP.isEmpty()){
            return Lists.newArrayList();
        }
        List<QueryBuilder> queryBuilderList = Lists.newArrayList();
        Iterator<Map.Entry<QueryTypeEnum, Query>> iter = QUERY_MAP.entrySet().iterator();
        while (iter.hasNext()){
            queryBuilderList.addAll(iter.next().getValue().buildQueryBuilderList(searchTypeParam));
        }
        return queryBuilderList;
    }
}
