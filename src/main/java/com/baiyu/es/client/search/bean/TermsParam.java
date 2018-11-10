package com.baiyu.es.client.search.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiyu
 * @description: TermsParam
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class TermsParam extends CommonParam {
    private static final long serialVersionUID = 1307195861427388020L;

    public TermsParam(String fieldName, List value) {
        super(fieldName, value);
    }
}
