package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.PrefixParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: PrefixQuery
 * @date: 2018/11/8
 */
@Component
public class PrefixQuery extends AbstractQuery<PrefixQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.PREFIX;
    }

    @Override
    public List<PrefixQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getPrefixParamList())){
            return Lists.newArrayList();
        }
        List<PrefixQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (PrefixParam prefixParam : searchTypeParam.getPrefixParamList()){
            if (null == prefixParam || CollectionUtils.isEmpty(prefixParam.getValue())){
                continue;
            }
            for (Object object: prefixParam.getValue()){
                queryBuilderList.add(QueryBuilders.prefixQuery(prefixParam.getFieldName(),object.toString()));
            }
        }
        return queryBuilderList;
    }
}
