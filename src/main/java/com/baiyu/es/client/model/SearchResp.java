package com.baiyu.es.client.model;

import lombok.Data;
import org.elasticsearch.search.aggregations.Aggregations;

import java.util.List;

/**
 * @author baiyu
 * @description: SearchResp  查询返回对象
 * @date: 2018/11/9
 */
@Data
public class SearchResp<T extends EsModel> {

    /**
     * 返回的数据
     */
    private List<T> esModelList;

    /**
     * 符合条件的所有数据条数
     */
    private long totalSize;

    /**
     * 统计返回数据
     */
    private Aggregations aggregations;
}
