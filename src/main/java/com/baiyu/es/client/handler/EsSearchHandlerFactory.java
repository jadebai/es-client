package com.baiyu.es.client.handler;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baiyu
 * @description: EsSearchHandlerHelper
 * @date: 2018/10/18
 */
@Component
public class EsSearchHandlerFactory {

    private static final ConcurrentHashMap<Class, AbstrateEsDataHandler> SEARCH_HANDLER_MAP = new ConcurrentHashMap<>();

    public static void addHandler(Class clazz, AbstrateEsDataHandler esDataHandler) {
        SEARCH_HANDLER_MAP.put(clazz, esDataHandler);
    }

    public static AbstrateEsDataHandler getHandler(Class clazz) {
        return SEARCH_HANDLER_MAP.get(clazz);
    }

}
