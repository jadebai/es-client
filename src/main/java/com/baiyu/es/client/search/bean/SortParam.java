package com.baiyu.es.client.search.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

import java.io.Serializable;

/**
 * @author baiyu
 * @description: SortParam
 * @date: 2018/11/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortParam implements Serializable {
    private static final long serialVersionUID = 7763025448532613898L;

    public SortParam(String filedName) {
        this.filedName = filedName;
        this.sortOrder = SortOrder.DESC;
    }

    private String filedName;

    private SortOrder sortOrder;
}
