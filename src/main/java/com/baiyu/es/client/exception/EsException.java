package com.baiyu.es.client.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baiyu
 * @description: EsException
 * @date: 2018/10/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EsException extends RuntimeException {

    private int errCode;
    private String errMsg;

    public EsException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public EsException(EsErrorCode esErrorCode) {
        this(esErrorCode.getErrCode(),esErrorCode.getErrMsg());
    }

}
