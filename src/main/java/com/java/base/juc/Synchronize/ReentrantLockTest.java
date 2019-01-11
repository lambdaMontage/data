package com.java.base.juc.Synchronize;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLock 可重入锁设置
 * @Author: shihao
 * @Date: 2018/12/28 19:35
 * @Version: 1.0
 **/
public class ReentrantLockTest {


    public static void test1() {

    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        //加锁
        reentrantLock.lock();
        try {
            // 业务逻辑
        } catch (Exception e) {

        } finally {
            // 释放锁
            reentrantLock.unlock();
        }
    }
}
