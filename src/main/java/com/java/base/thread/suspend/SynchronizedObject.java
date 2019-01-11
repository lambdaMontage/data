package com.java.base.thread.suspend;

/**
 * Created by shihao 2018/3/12 15:03
 */
public class SynchronizedObject {

    synchronized public void printString(){
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远suspend了!");
            Thread.currentThread().suspend();
        }
        System.out.println("end ");
    }
}
