package com.baiyu.es.client.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author baiyu
 * @description: LogBackTest
 * @date: 2018/11/10
 */
@Slf4j
public class LogBackTest {

    @Test
    public void test(){
        log.info("测试");
        log.error("报错了");
    }
}
