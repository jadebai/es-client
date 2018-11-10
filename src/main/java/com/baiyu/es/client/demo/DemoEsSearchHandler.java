package com.baiyu.es.client.demo;

import com.baiyu.es.client.handler.AbstractEsSearchHandler;
import org.springframework.stereotype.Component;

/**
 * @author baiyu
 * @description: DeFaultEsMultiSearchHandler
 * @date: 2018/10/21
 */
@Component
public class DemoEsSearchHandler extends AbstractEsSearchHandler<DemoEsModel> {

    /**
     * 获取索引名称  相当于数据库-库
     *
     * @return
     */
    @Override
    public String getIndexName() {
        return "advertisement";
    }

    /**
     * 获取类型名称  相当于数据库-表
     *
     * @return
     */
    @Override
    public String getTypeName() {
        return "advertisement";
    }
}
