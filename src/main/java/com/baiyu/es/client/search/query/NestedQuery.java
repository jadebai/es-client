package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.NestedParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: NestedQuery
 * @date: 2018/11/8
 */
@Component
public class NestedQuery extends AbstractQuery<NestedQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.NESTED;
    }

    @Override
    public List<NestedQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getNestedParamList())){
            return Lists.newArrayList();
        }
        List<NestedQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (NestedParam nestedParam : searchTypeParam.getNestedParamList()){
            if (null == nestedParam){
                continue;
            }
            queryBuilderList.addAll(builderOneNestedQuery(nestedParam));
        }
        return queryBuilderList;
    }

    private List<NestedQueryBuilder> builderOneNestedQuery(NestedParam nestedParam) {
        List<NestedQueryBuilder> nestedQueryBuilderList = Lists.newArrayList();
        if (null != nestedParam.getSearchTypeParam()){
            return nestedQueryBuilderList;
        }
        List<QueryBuilder> queryBuilderList = QueryFactory.queryBuilderList(nestedParam.getSearchTypeParam());
        if (CollectionUtils.isEmpty(queryBuilderList)){
            return nestedQueryBuilderList;
        }
        for (QueryBuilder queryBuilder : queryBuilderList){
            nestedQueryBuilderList.add(QueryBuilders.nestedQuery(nestedParam.getFieldName(),queryBuilder));
        }
        return nestedQueryBuilderList;
    }
}
