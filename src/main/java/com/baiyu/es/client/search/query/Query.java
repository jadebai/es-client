package com.baiyu.es.client.search.query;


import com.baiyu.es.client.search.bean.SearchTypeParam;
import com.baiyu.es.client.search.enums.QueryTypeEnum;

import java.util.List;

/**
 * @author baiyu
 * @description: Query
 * @date: 2018/11/9
 */
public interface Query<T> {

    /**
     * 设置查询类型
     *
     * @return
     */
    public QueryTypeEnum getQueryTypeEnum();

    /**
     * 处理对应查询类型的参数  生成 query
     *
     * @param searchTypeParam
     * @return
     */
    List<T> buildQueryBuilderList(SearchTypeParam searchTypeParam);
}
