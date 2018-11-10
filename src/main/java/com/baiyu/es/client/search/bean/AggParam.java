package com.baiyu.es.client.search.bean;

import com.baiyu.es.client.search.enums.AggTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baiyu
 * @description: AggParam
 * @date: 2018/11/9
 */
@Data
@Builder
public class AggParam implements Serializable{
    private static final long serialVersionUID = -4917463373318205794L;

    /**
     * 聚合的类型
     */
    private AggTypeEnum aggTypeEnum;

    /**
     * 聚合字段
     */
    private String fileName;

    /**
     * 聚合别名
     */
    private String aggName;

    /**
     * 子聚合列表
     */
    private List<AggParam> subAggParam;

    /**
     * 排序的自聚合下标  null 为不排序
     */
    private Integer indexOrder;

    /**
     * 正序还是倒叙 true 正序
     */
    private boolean orderFlag;

    /**
     * 取多少条统计
     */
    private int size;

    /**
     *  查询条件  最少几条才查询出来
     */
    private int minDocCount;
}
