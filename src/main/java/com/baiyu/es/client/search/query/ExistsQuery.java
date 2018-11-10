package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: ExistsQuery
 * @date: 2018/11/8
 */
@Component
public class ExistsQuery extends AbstractQuery<ExistsQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.EXISTS;
    }

    @Override
    public List<ExistsQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getExistsParamList())){
            return Lists.newArrayList();
        }
        List<ExistsQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (String exists : searchTypeParam.getExistsParamList()){
            if (StringUtils.isEmpty(exists)){
                continue;
            }
            queryBuilderList.add(QueryBuilders.existsQuery(exists));
        }
        return queryBuilderList;
    }
}
