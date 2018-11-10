package com.baiyu.es.client.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author baiyu
 * @description: RegisterBeanDefinitionUtil  注册bean到spring容器 工具类
 * @date: 2018/11/5
 */
public class RegisterBeanDefinitionUtil {

    public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry, String beanName,
                                                            Class<?> beanClass) {
        if (registry.containsBeanDefinition(beanName)) {
            return false;
        }
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
        return true;
    }
}
