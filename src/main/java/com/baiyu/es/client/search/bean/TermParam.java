package com.baiyu.es.client.search.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiyu
 * @description: TermParam  不分词匹配
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class TermParam extends CommonParam {
    private static final long serialVersionUID = 2531138328589531602L;

    public TermParam(String fieldName, List value) {
        super(fieldName, value);
    }
}
