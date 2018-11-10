package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.bean.StringQueryParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: StringQuery
 * @date: 2018/11/8
 */
@Component
public class StringQuery extends AbstractQuery<QueryStringQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.STRING;
    }

    @Override
    public List<QueryStringQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getStringQueryParamList())){
            return Lists.newArrayList();
        }
        List<QueryStringQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (StringQueryParam stringQueryParam : searchTypeParam.getStringQueryParamList()){
            if (null == stringQueryParam || CollectionUtils.isEmpty(stringQueryParam.getValue())){
                continue;
            }
            for (Object object: stringQueryParam.getValue()) {
                queryBuilderList.add(QueryBuilders.queryStringQuery(QueryParser.escape(object.toString())).defaultOperator(stringQueryParam.getOperator())
                        .queryName(stringQueryParam.getFieldName()).minimumShouldMatch(stringQueryParam.getMinimumShouldMatch()));
            }
        }
        return queryBuilderList;
    }
}
