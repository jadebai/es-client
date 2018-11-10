package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.MatchParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: MatchQuery
 * @date: 2018/11/8
 */
@Component
public class MatchQuery extends AbstractQuery<MatchQueryBuilder>{

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.MATCH;
    }

    @Override
    public List<MatchQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getMatchParamList())){
            return Lists.newArrayList();
        }
        List<MatchQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (MatchParam matchParam : searchTypeParam.getMatchParamList()){
            if (null == matchParam || CollectionUtils.isEmpty(matchParam.getValue())){
                continue;
            }
            for (Object object: matchParam.getValue()){
                queryBuilderList.add(QueryBuilders.matchQuery(matchParam.getFieldName(),object));
            }
        }
        return queryBuilderList;
    }
}
