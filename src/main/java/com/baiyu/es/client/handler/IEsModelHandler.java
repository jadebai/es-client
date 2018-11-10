package com.baiyu.es.client.handler;

import com.baiyu.es.client.model.EsModel;

import java.util.List;

/**
 * @author baiyu
 * @description: IEsModelHandler  ES返回的数据对象处理接口
 * @date: 2018/10/22
 */
public interface IEsModelHandler<T extends EsModel> {

    /**
     * check 参数
     *
     * @param model
     */
    void checkModel(T model);

    /**
     * 处理单个数据
     *
     * @param model
     */
    T handler(T model, T oldModel);

    /**
     * 处理批量数据
     *
     * @param models
     */
    List<T> handlerList(List<T> models);
}
