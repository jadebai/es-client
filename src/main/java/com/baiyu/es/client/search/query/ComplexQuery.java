package com.baiyu.es.client.search.query;

import com.baiyu.es.client.search.bean.ComplexParam;
import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.builder.SearchRequestBuilderFactory;
import com.baiyu.es.client.search.enums.QueryTypeEnum;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baiyu
 * @description: ComplexQuery
 * @date: 2018/11/8
 */
@Component
public class ComplexQuery extends AbstractQuery<BoolQueryBuilder> {

    /**
     * 设置查询类型
     *
     * @return
     */
    @Override
    public QueryTypeEnum getQueryTypeEnum() {
        return QueryTypeEnum.COMPLEX;
    }

    /**
     * 处理对应查询类型的参数  生成 query
     *
     * @param searchTypeParam
     * @return
     */
    @Override
    public List<BoolQueryBuilder> buildQueryBuilderList(SearchTypeParam searchTypeParam) {
        if (null == searchTypeParam || CollectionUtils.isEmpty(searchTypeParam.getComplexParamList())){
            return Lists.newArrayList();
        }
        List<BoolQueryBuilder> queryBuilderList = Lists.newArrayList();
        for (ComplexParam complexParam : searchTypeParam.getComplexParamList()){
            if (null == complexParam || complexParam.getSearchType().isEmpty()){
                continue;
            }
            queryBuilderList.add(SearchRequestBuilderFactory.searchBuilder(null,complexParam.getSearchType()).getBoolQueryBuilder());
        }
        return queryBuilderList;
    }
}
