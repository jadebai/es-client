package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.RangeParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.baiyu.es.client.util.ObjectsUtil;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: RangeQuery
 * @date: 2018/11/8
 */
@Component
public class RangeQuery extends AbstractQuery<RangeQueryBuilder> {

    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.RANGE;
    }

    @Override
    public List<RangeQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getRangeParamList())){
            return Lists.newArrayList();
        }
        List<RangeQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (RangeParam rangeParam : searchTypeParam.getRangeParamList()){
            if (ObjectsUtil.rangeParamIsEmpty(rangeParam)){
                continue;
            }
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(rangeParam.getFieldName());
            if (!StringUtils.isEmpty(rangeParam.getGteValue())){
                rangeQueryBuilder.gte(rangeParam.getGteValue());
            }
            if (!StringUtils.isEmpty(rangeParam.getGtValue())){
                rangeQueryBuilder.gt(rangeParam.getGtValue());
            }
            if (!StringUtils.isEmpty(rangeParam.getLteValue())){
                rangeQueryBuilder.lte(rangeParam.getLteValue());
            }
            if (!StringUtils.isEmpty(rangeParam.getLtValue())){
                rangeQueryBuilder.lt(rangeParam.getLtValue());
            }
            queryBuilderList.add(rangeQueryBuilder);
        }
        return queryBuilderList;
    }
}
