package com.baiyu.es.client.exception;

/**
 * @author baiyu
 * @description: EsErrorCode
 * @date: 2018/10/22
 */
public enum EsErrorCode{
    ES_PARRAM_ERROR(1001,"ES请求参数异常"),
    ES_JSON_ERROR(1002,"ES数据JSON转换异常"),
    ES_UPSET_ERROR(1003,"ES增加或修改数据异常"),
    ES_ADDRESSES_ERROR(1004,"ES-addresses数据异常"),
    ;

    private int errCode;
    private String errMsg;

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    EsErrorCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
