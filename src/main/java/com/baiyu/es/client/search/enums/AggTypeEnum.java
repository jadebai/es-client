package com.baiyu.es.client.search.enums;

import com.baiyu.es.client.search.bean.AggParam;
import com.baiyu.es.client.util.ObjectsUtil;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.HistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.util.CollectionUtils;

/**
 * @author baiyu
 * @description: AggTypeEnum
 * @date: 2018/11/9
 */
public enum AggTypeEnum {

    max{
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.max(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    min {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.min(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    avg {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.avg(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    sum {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.sum(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    stats {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.stats(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    count {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return AggregationBuilders.count(aggParam.getAggName()).field(aggParam.getAggName());
        }
    },
    range {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            RangeBuilder rangeBuilder = AggregationBuilders.range(aggParam.getAggName()).field(aggParam.getAggName());
            for (AggParam subAggParam : ObjectsUtil.safeNull(aggParam.getSubAggParam())){
                rangeBuilder.subAggregation(subAggParam.getAggTypeEnum().builder(subAggParam));
            }
            return rangeBuilder;
        }
    },
    terms {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            TermsBuilder termsBuilder = AggregationBuilders.terms(aggParam.getAggName()).field(aggParam.getAggName())
                                            .size(aggParam.getSize()).minDocCount(aggParam.getMinDocCount());
            if (CollectionUtils.isEmpty(aggParam.getSubAggParam())){
                return termsBuilder;
            }
            if (null != aggParam.getIndexOrder() && aggParam.getSubAggParam().size() > aggParam.getIndexOrder()){
                termsBuilder.order(Terms.Order.aggregation(aggParam.getSubAggParam().get(aggParam.getIndexOrder()).getAggName(), false));
            }
            for (AggParam subAggParam : ObjectsUtil.safeNull(aggParam.getSubAggParam())){
                termsBuilder.subAggregation(subAggParam.getAggTypeEnum().builder(subAggParam));
            }
            return termsBuilder;
        }
    },
    dateRange {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return null;
        }
    },
    children {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return null;
        }
    },
    missing {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return null;
        }
    },
    nested {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return null;
        }
    },
    histogram {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            HistogramBuilder histogramBuilder = AggregationBuilders.histogram(aggParam.getAggName()).field(aggParam.getAggName())
                    .minDocCount(aggParam.getMinDocCount());
            for (AggParam subAggParam : ObjectsUtil.safeNull(aggParam.getSubAggParam())){
                histogramBuilder.subAggregation(subAggParam.getAggTypeEnum().builder(subAggParam));
            }
            return histogramBuilder;
        }
    },
    dateHistogram {
        @Override
        public AbstractAggregationBuilder builder(AggParam aggParam) {
            return null;
        }
    },
    ;

    public abstract AbstractAggregationBuilder builder(AggParam aggParam);
}
