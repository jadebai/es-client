package com.baiyu.es.client.search.agg;

import com.baiyu.es.client.search.bean.AggParam;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;

/**
 * @author baiyu
 * @description: AggBuilder
 * @date: 2018/11/9
 */
public class AggBuilder {

    private AggParam aggParam;

    public AggBuilder(AggParam aggParam) {
        this.aggParam = aggParam;
    }

    public AbstractAggregationBuilder builder(){
        return aggParam.getAggTypeEnum().builder(aggParam);
    }
}
