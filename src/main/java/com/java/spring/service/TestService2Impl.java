package com.java.spring.service;

import com.java.spring.annotation.DhService;

@DhService
public class TestService2Impl implements TestService2 {
    @Override
    public void doServiceTest() {
        System.out.println("业务层执行方法了2222222222222");
    }
}
