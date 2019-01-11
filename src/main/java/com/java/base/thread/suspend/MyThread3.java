package com.java.base.thread.suspend;


/**
 * 守护线程
 * Created by shihao 2018/3/12 15:55
 */
public class MyThread3 extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i= " + (i));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Run {
        public static void main(String[] args) {
            try {
                MyThread3 myThread3 = new MyThread3();
                myThread3.setDaemon(true);
                myThread3.start();
                Thread.sleep(1000);
                System.out.println("我离开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
