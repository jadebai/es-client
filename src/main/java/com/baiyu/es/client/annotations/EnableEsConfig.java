package com.baiyu.es.client.annotations;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author baiyu
 * @description: EnableEsConfig
 * @date: 2018/11/2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(EsConfigRegistrar.class)
public @interface EnableEsConfig {

    //ES 地址
    String socketAddressStrings();

    //ES 集群名称
    String clusterName();

    //开启集群嗅探
    String sniff() default "true";

}
