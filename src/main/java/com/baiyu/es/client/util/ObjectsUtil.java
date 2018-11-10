package com.baiyu.es.client.util;

import com.baiyu.es.client.search.bean.RangeParam;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author baiyu
 * @description: ObjectsUtil
 * @date: 2018/11/7
 */
@SuppressWarnings("unchecked")
public class ObjectsUtil<T> {

    public static List<String> initStringList(String string){
        if (StringUtils.isEmpty(string)){
            return Lists.newArrayList();
        }
        return Lists.newArrayList(string);
    }

    public static <T> List<T> initObjectList(T t){
        if (null == t){
            return Lists.newArrayList();
        }
        return Lists.<T>newArrayList(t);
    }

    public static boolean rangeParamIsEmpty(RangeParam rangeParam){
        return (null == rangeParam.getGteValue() && null == rangeParam.getGtValue()
                && null == rangeParam.getLteValue() && null == rangeParam.getLtValue());
    }

    public static <T> Iterable<T> safeNull(Iterable<T> iterable) {
        return iterable != null ?
                iterable :
                new FluentIterable<T>() {
                    @Override
                    public Iterator<T> iterator() {
                        return Collections.emptyIterator();
                    }
                };
    }
}
