package com.java.base.thread.synchroized;

/**
 * 方法内的变量为线程安全
 * Created by shihao 2018/3/12 16:27
 */
public class HasSelfPrivateNum {
    //    public void add(String username) throws InterruptedException {
//        int num = 0;
//        if (username.equals("a")) {
//            num = 100;
//            System.out.println("a set over!");
//            Thread.sleep(1000);
//        } else {
//            num = 200;
//            System.out.println("b set over2");
//        }
//        System.out.println(username + "num = " + num);
//    }
    private int num = 0;


    synchronized public void add(String username) throws InterruptedException {
        int num = 0;
        if (username.equals("a")) {
            num = 100;
            System.out.println("a set over!");
            Thread.sleep(2000);
        } else {
            num = 200;
            System.out.println("b set over2");
        }
        System.out.println(username + "num = " + num);
    }


    static class ThreadA extends Thread {
        private HasSelfPrivateNum hasSelfPrivateNum;

        public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
            super();
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            super.run();
            try {
                hasSelfPrivateNum.add("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        private HasSelfPrivateNum hasSelfPrivateNum;

        public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
            super();
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            super.run();
            try {
                hasSelfPrivateNum.add("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 方法与锁对象
     */
   synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadC extends Thread {
        private HasSelfPrivateNum hasSelfPrivateNum;

        public ThreadC(HasSelfPrivateNum hasSelfPrivateNum) {
            super();
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            super.run();
            hasSelfPrivateNum.methodA();
        }
    }


    static class ThreadD extends Thread {
        private HasSelfPrivateNum hasSelfPrivateNum;

        public ThreadD(HasSelfPrivateNum hasSelfPrivateNum) {
            super();
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            super.run();
            hasSelfPrivateNum.methodA();
        }
    }
}
