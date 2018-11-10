package com.baiyu.es.client.search.bean;

import lombok.Data;

import java.util.List;

/**
 * @author baiyu
 * @description: PrefixParam
 * @date: 2018/11/8
 */
@Data
public class PrefixParam extends CommonParam {

    public PrefixParam(String fieldName, List value) {
        super(fieldName, value);
    }
}
