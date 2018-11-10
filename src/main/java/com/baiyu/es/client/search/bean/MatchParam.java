package com.baiyu.es.client.search.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiyu
 * @description: MatchParam  分词匹配
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class MatchParam extends CommonParam {
    private static final long serialVersionUID = -1399939049019408851L;

    public MatchParam(String fieldName, List value) {
        super(fieldName, value);
    }
}
