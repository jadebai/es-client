package com.baiyu.es.client.search.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author baiyu
 * @description: CommonParam
 * @date: 2018/11/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonParam implements Serializable {
    private static final long serialVersionUID = -8669890623175575695L;

    /**
     * 查询的字段
     */
    private String fieldName;

    /**
     * 查询的值
     */
    private List value;
}
