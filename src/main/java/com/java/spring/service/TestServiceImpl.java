package com.java.spring.service;

import com.java.spring.annotation.DhService;

@DhService("testService")
public class TestServiceImpl implements TestService {

    @Override
    public void doServiceTest() {
        System.out.println("业务层执行方法了。。。");
    }
}
