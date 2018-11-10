package com.baiyu.es.client.model;

import lombok.Data;
import org.elasticsearch.client.Client;

/**
 * @author baiyu
 * @description: EsClient  Client 包装类
 * @date: 2018/10/19
 */
@Data
public class EsClient {
    private Client client;
}
