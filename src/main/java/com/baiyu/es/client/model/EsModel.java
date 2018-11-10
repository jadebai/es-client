package com.baiyu.es.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author baiyu
 * @description: EsModel  ES所有对象的父类  必须被继承
 * @date: 2018/10/21
 */
@Data
public class EsModel implements Serializable{
    private static final long serialVersionUID = 7668456237585047103L;

    /**
     * 每条数据的主键ID
     */
    private String id;

    /**
     * 如果是父子关系  父ID
     */
    @JsonIgnore
    private String parentId;

}
