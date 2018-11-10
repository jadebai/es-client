package com.baiyu.es.client.search.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiyu
 * @description: RangeParam  区间大小匹配
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class RangeParam extends CommonParam {
    private static final long serialVersionUID = -6536339473549639905L;

    @Builder
    public RangeParam(String fieldName, List value, Object gtValue, Object gteValue,
                      Object ltValue, Object lteValue, String timeZone) {
        super(fieldName, value);
        this.gtValue = gtValue;
        this.gteValue = gteValue;
        this.ltValue = ltValue;
        this.lteValue = lteValue;
        this.timeZone = timeZone;
    }

    /**

     * 大于的值
     */
    private Object gtValue;

    /**
     * 大于等于的值
     */
    private Object gteValue;

    /**
     * 小大于的值
     */
    private Object ltValue;

    /**
     * 小于等于的值
     */
    private Object lteValue;

    private String timeZone;

}
