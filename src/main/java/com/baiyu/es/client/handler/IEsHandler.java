package com.baiyu.es.client.handler;

import org.elasticsearch.client.Client;

/**
 * @author baiyu
 * @description: IEsHandler
 * @date: 2018/10/21
 */
public interface IEsHandler {

    /**
     * 获取ES的Client
     * @return
     */
    Client getClient();
}
