package com.baiyu.es.client.search.bean;

import com.baiyu.es.client.constant.Constant;
import com.baiyu.es.client.search.enums.SearchTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author baiyu
 * @description: SearchParam 查询条件 对象
 * @date: 2018/11/6
 */
@Data
@Builder
public class SearchParam implements Serializable{
    private static final long serialVersionUID = 2397814426838933725L;

    /**
     * 当前页码
     */
    @Builder.Default
    private Integer pageIndex = Constant.PAGE_INDEX;

    /**
     * 每页条数
     */
    @Builder.Default
    private Integer pageSize = Constant.PAGE_SIZE;

    /**
     * 排序字段
     */
    private List<SortParam> sortParamList;

    /**
     * 统计字段
     */
    private List<AggParam> aggParamList;

    /**
     * 查询参数
     */
    private Map<SearchTypeEnum,SearchTypeParam> searchType;


}
