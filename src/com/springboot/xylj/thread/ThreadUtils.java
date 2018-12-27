package com.springboot.xylj.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: synchronized 的三种用法
 * @Author: shihao
 * @Date: 2018/12/26 16:32
 * @Version: 1.0
 **/
public class ThreadUtils implements Runnable {

    static ThreadUtils threadUtils = new ThreadUtils();

    static int a = 0;

    /**
     *  直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁
     *  两个线程同时使用一个实例变量 是同步的
     */
    public synchronized void increase() {
        a++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(threadUtils);
        Thread thread1 = new Thread(threadUtils);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(a);

        new ReentrantLock().lock();
    }
}
