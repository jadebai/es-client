package com.baiyu.es.client.util;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.SystemPropertyUtils;

/**
 * @author baiyu
 * @description: PlacehodlerUtil  占位符处理 工具类
 * @date: 2018/11/5
 */
public class PlacehodlerUtil {

    public static String convertPlacehodler(String value,ConfigurableEnvironment environment){
        if (value.startsWith(SystemPropertyUtils.PLACEHOLDER_PREFIX) && value.endsWith(SystemPropertyUtils.PLACEHOLDER_SUFFIX)){
            value = environment.getProperty(value.replace(SystemPropertyUtils.PLACEHOLDER_PREFIX,"")
                    .replace(SystemPropertyUtils.PLACEHOLDER_SUFFIX,""));
        }
        return value;
    }

}
