package com.baiyu.es.client.search.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baiyu
 * @description: SearchTypeParam  每个查询类型的对象
 * @date: 2018/11/6
 */
@Data
@Builder
public class SearchTypeParam implements Serializable {
    private static final long serialVersionUID = -1293568560013833723L;

    /**
     * 是否需要打分
     */
    @Builder.Default
    private boolean isScore = true;

    /**
     * 范围查询值
     */
    private List<RangeParam> rangeParamList;

    /**
     * 不分词匹配查询
     */
    private List<TermParam> termParamList;

    /**
     * 分词匹配查询
     */
    private List<MatchParam> matchParamList;

    /**
     * 不分词 数组 匹配查询
     */
    private List<TermsParam> termsParamList;

    /**
     * 字符串匹配查询
     */
    private List<StringQueryParam> stringQueryParamList;


    /**
     * nested 查询
     */
    private List<NestedParam> nestedParamList;

    /**
     * 前缀 查询
     */
    private List<PrefixParam> prefixParamList;

    /**
     * 存在 查询
     */
    private List<String> existsParamList;

    /**
     * 复杂多级 查询
     */
    private List<ComplexParam> complexParamList;

}
