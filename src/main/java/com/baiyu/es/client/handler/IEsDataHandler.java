package com.baiyu.es.client.handler;

import com.baiyu.es.client.model.EsModel;
import com.baiyu.es.client.model.SearchResp;
import com.baiyu.es.client.search.bean.SearchParam;

import java.util.List;

/**
 * @author baiyu
 * @description: IEsDataHandler  ES 数据操作接口
 * @date: 2018/10/19
 */
public interface IEsDataHandler<T extends EsModel> {

    /**
     * 获取索引名称  相当于数据库-库
     * @return
     */
    String getIndexName();

    /**
     * 获取类型名称  相当于数据库-表
     * @return
     */
    String getTypeName();

    /**
     * 添加或者修改单条数据
     * @param model
     * @return
     */
    T upset(T model);

    /**
     * 批量添加或者修改数据
     *
     * @param models
     * @return
     */
    List<T> upsetList(List<T> models);

    /**
     * 根据ID获取单条数据
     * @param id
     * @return
     */
    T getDataById(String id);

    /**
     * 根据IDS获取批量数据
     * @param ids
     * @return
     */
    List<T> getDatasByIds(List<String> ids);

    /**
     * 根据条件搜索数据
     * @param param
     * @return
     */
    SearchResp<T> search(SearchParam param);


    /**
     * 根据ID 删除数据
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 根据IDS  批量删除数据
     * @param ids
     * @return
     */
    boolean deleteByIds(List<String> ids);


}
