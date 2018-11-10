package com.baiyu.es.client.search.bean;

import com.baiyu.es.client.search.enums.SearchTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author baiyu
 * @description: ComplexParam
 * @date: 2018/11/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexParam implements Serializable {
    private static final long serialVersionUID = -5209609633362735806L;

    /**
     * 查询参数
     */
    private Map<SearchTypeEnum,SearchTypeParam> searchType;
}
