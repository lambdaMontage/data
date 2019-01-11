package com.java.base.juc.Concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/3 10:33
 * @Version: 1.0
 **/
public class CyclicBarrierTest {

    private static CyclicBarrier c = new CyclicBarrier(2,new A());

    /**
     * 更高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)
     * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
     * @Response 3 1 2
     * @param args
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(()->{
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);

        }).start();
        c.await();
        System.out.println(2);
    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }

//    public static void main(String[] args) {
//        new Thread(()->{
//            try {
//                c.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            System.out.println(1);
//        }).start();
//        try {
//            c.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
//        System.out.println(2);
//    }

}
