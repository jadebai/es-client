package com.baiyu.es.client.handler;

import com.baiyu.es.client.model.EsClient;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author baiyu
 * @description: AbstractEsHandler ES 操作抽象类
 * @date: 2018/10/19
 */
public abstract class AbstractEsHandler implements IEsHandler, AutoCloseable {

    /**
     * EsClient封装类
     */
    @Autowired
    private EsClient esClient;

    @Override
    public final Client getClient() {
        return esClient.getClient();
    }

    /**
     * 关闭ES连接
     */
    @Override
    public final void close() throws Exception {
        esClient.getClient().close();
    }
}
