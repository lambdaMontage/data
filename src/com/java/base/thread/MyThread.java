package com.java.base.thread;


/**
 * 线程测试
 * Created by shihao 2018/3/12 9:54
 */
public class MyThread extends Thread {
    private int count = 5;

    @Override
    public synchronized void run() {
        super.run();
        count--;
        System.out.println("由" + currentThread().getName() + "计算 count=" + count);
    }


    /**
     * 多个线程共享同一个实例变量，会有"非线程安全"的问题
     * 加上synchronized之后是线程安全的
     */
    static class Run {
        public static void main(String[] args) {
            MyThread myThread = new MyThread();
            Thread a = new Thread(myThread, "a");
            Thread b = new Thread(myThread, "b");
            Thread c = new Thread(myThread, "c");
            Thread d = new Thread(myThread, "d");
            Thread e = new Thread(myThread, "e");
            a.start();
            b.start();
            c.start();
            d.start();
            e.start();
        }
    }

    static class MyThread3 extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("run threadName= " + currentThread().getName() + "begin");
                Thread.sleep(3000);
                System.out.println("run threadName= " + currentThread().getName() + "end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sleep()休眠线程
     */
    static class Run1 {
        public static void main(String[] args) {
            MyThread3 myThread3 = new MyThread3();
            System.out.println("begin = " + System.currentTimeMillis());
            myThread3.start();
            System.out.println("end = " + System.currentTimeMillis());
        }
    }

    static class Mythread4 extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 400; i++) {
                System.out.println("i =" + (i + 1));
            }
        }
    }

    /**
     * interrputed()测试当前线程是否中断,将状态标志清除为false
     * isInterrputed()测试线程对象thread是否已经中断，但不清楚状态标记
     */
    static class Run2 {
        public static void main(String[] args) {
            try {
                Mythread4 mythread4 = new Mythread4();
                mythread4.start();
                Thread.sleep(4000);
                mythread4.interrupt();
                System.out.println("是否停止1?" + Mythread4.interrupted());
                System.out.println("是否停止2？" + Mythread4.interrupted());
            } catch (InterruptedException e) {
                System.out.println("main catch");
                e.printStackTrace();
            }
        }
    }

    static class Mythread5 extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 500; i++) {
                if (Mythread5.interrupted()) {
                    System.out.println("已经停止了！我要退出了");
                    break;
                }
                System.out.println("i = " + (i + 1));
            }
        }
    }

    /**
     * 异常停止当前线程
     */
    static class Run5 {
        public static void main(String[] args) {
            try {
                Mythread5 mythread5 = new Mythread5();
                mythread5.start();
                Thread.sleep(3000);
                mythread5.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end!");
        }
    }

    static class Mythread6 extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                for (int i = 0; i < 50000; i++) {
                    if (Mythread5.interrupted()) {
                        System.out.println("已经是停止状态，我要退出");
                        throw new InterruptedException();
                    }
                    System.out.println("i =" + (i + 1));
                }
                System.out.println("我在for下面");
            } catch (InterruptedException e) {
                System.out.println("进java类run方法的crash了");
                e.printStackTrace();
            }
        }
    }

    static class Run6 {
        public static void main(String[] args) {
            try {
                Mythread6 mythread6 = new Mythread6();
                mythread6.start();
                Thread.sleep(3000);
                mythread6.interrupt();
            } catch (InterruptedException e) {
                System.out.println("main catch");
                e.printStackTrace();
            }
        }
    }
}
