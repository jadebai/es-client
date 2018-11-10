package com.baiyu.es.client.search.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baiyu
 * @description: NestedParam
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
public class NestedParam extends CommonParam {
    private static final long serialVersionUID = -6459784664295043449L;

    private SearchTypeParam searchTypeParam;

    @Builder
    public NestedParam(String fieldName, List value, SearchTypeParam searchTypeParam) {
        super(fieldName, value);
        this.searchTypeParam = searchTypeParam;
    }

}
