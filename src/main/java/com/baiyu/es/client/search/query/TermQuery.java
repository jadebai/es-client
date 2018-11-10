package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.bean.TermParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: TermQuery
 * @date: 2018/11/8
 */
@Component
public class TermQuery extends AbstractQuery<TermQueryBuilder> {
    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.TERM;
    }

    @Override
    public List<TermQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getTermParamList())){
            return Lists.newArrayList();
        }
        List<TermQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (TermParam termParam : searchTypeParam.getTermParamList()){
            if (null == termParam || CollectionUtils.isEmpty(termParam.getValue())){
                continue;
            }
            for (Object object: termParam.getValue()){
                queryBuilderList.add(QueryBuilders.termQuery(termParam.getFieldName(),object));
            }
        }
        return queryBuilderList;
    }
}
