package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.bean.TermsParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: TermsQuery
 * @date: 2018/11/8
 */
@Component
public class TermsQuery extends AbstractQuery<TermsQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.TERMS;
    }

    @Override
    public List<TermsQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getTermsParamList())){
            return Lists.newArrayList();
        }
        List<TermsQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (TermsParam termsParam : searchTypeParam.getTermsParamList()){
            if (null == termsParam || CollectionUtils.isEmpty(termsParam.getValue())){
                continue;
            }
            queryBuilderList.add(QueryBuilders.termsQuery(termsParam.getFieldName(), termsParam.getValue()));
        }
        return queryBuilderList;
    }
}
