package com.baiyu.es.client.search.query;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author baiyu
 * @description: AbstractQuery
 * @date: 2018/11/8
 */
public abstract class AbstractQuery<T extends QueryBuilder> implements Query<T>,InitializingBean{

    @Override
    public void afterPropertiesSet() throws Exception {
        QueryFactory.addQuery(getQueryTypeEnum(),this);
    }
}
