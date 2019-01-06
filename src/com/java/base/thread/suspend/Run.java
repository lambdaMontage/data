package com.java.base.thread.suspend;

/**
 * Created by shihao 2018/3/12 15:06
 */
public class Run {
    public static void main(String[] args) {
        try {
            final SynchronizedObject synchronizedObject = new SynchronizedObject();
            Thread thread1 = new Thread(() -> synchronizedObject.printString());
            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);
            Thread thread2 = new Thread(() -> {
                System.out.println("thread2 启动了 ");
                System.out.println("printString方法被线程a锁定");
                synchronizedObject.printString();
            });
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
