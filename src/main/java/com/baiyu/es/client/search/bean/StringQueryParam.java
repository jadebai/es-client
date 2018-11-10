package com.baiyu.es.client.search.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

import java.util.List;

/**
 * @author baiyu
 * @description: StringQueryParam
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class StringQueryParam extends CommonParam {
    private static final long serialVersionUID = 6750568284901168229L;

    public StringQueryParam(String fieldName, List value, String minimumShouldMatch) {
        this(fieldName,value,minimumShouldMatch,QueryStringQueryBuilder.Operator.AND);
    }

    public StringQueryParam(String fieldName, List value) {
        this(fieldName,value,"100%");
    }

    public StringQueryParam(String fieldName, List value, String minimumShouldMatch, QueryStringQueryBuilder.Operator operator) {
        super(fieldName, value);
        this.minimumShouldMatch = minimumShouldMatch;
        this.operator = operator;
    }

    /**
     * 匹配度
     */
    private String minimumShouldMatch;

    /**
     * 操作关系
     */
    private QueryStringQueryBuilder.Operator operator;
}
