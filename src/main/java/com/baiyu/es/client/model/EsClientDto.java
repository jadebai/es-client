package com.baiyu.es.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author baiyu
 * @description: EsClientDto  保存ES CLIENT  初始化的参数  中转类
 * @date: 2018/10/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsClientDto implements Serializable{
    private static final long serialVersionUID = 1911581843011332569L;

    /**
     * ES 连接地址  172.21.247.89:9300
     */
    private String socketAddressStrings;

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 是否开启集群嗅探  默认 “true”
     */
    private String sniff;
}
