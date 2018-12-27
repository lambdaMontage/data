package com.springboot.xylj.thread.synchroized;

/**
 * Created by shihao 2018/3/12 15:06
 */
public class Run {
    //    public static void main(String[] args) {
//        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
//        HasSelfPrivateNum.ThreadA thread = new HasSelfPrivateNum.ThreadA(hasSelfPrivateNum);
//        thread.start();
//        HasSelfPrivateNum.ThreadB threadB = new HasSelfPrivateNum.ThreadB(hasSelfPrivateNum);
//        threadB.start();
//    }

    /**
     * 一个对象一把锁，多个对象多把锁，信息都保存在各自线程栈中，对其他线程不可见，所以 AB并发执行。结果是异步
     * 方法上加入static关键字修饰，相当于class加锁。线程会共享资源，结果是同步
     * @param args
     */
//    public static void main(String[] args) {
//        HasSelfPrivateNum hasSelfPrivateNum1 = new HasSelfPrivateNum();
//        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();
//        HasSelfPrivateNum.ThreadA threadA = new HasSelfPrivateNum.ThreadA(hasSelfPrivateNum1);
//        threadA.start();
//        HasSelfPrivateNum.ThreadB threadB = new HasSelfPrivateNum.ThreadB(hasSelfPrivateNum2);
//        threadB.start();
//    }

    /**
     * 加synchroized关键字，方法排队进行，共享资源才需要同步化
     * @param args
     */
//    public static void main(String[] args) {
//        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
//        HasSelfPrivateNum.ThreadA a = new HasSelfPrivateNum.ThreadA(hasSelfPrivateNum);
//        a.setName("A");
//        HasSelfPrivateNum.ThreadB b = new HasSelfPrivateNum.ThreadB(hasSelfPrivateNum);
//        b.setName("B");
//        a.start();
//        b.start();
//    }

    /**
     * synchronized 方法与锁对象 线程锁的是对象.加了synchronized之后排队进入方法
     * @param args
     */
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum.ThreadC threadC = new HasSelfPrivateNum.ThreadC(hasSelfPrivateNum);
        threadC.setName("C");
        HasSelfPrivateNum.ThreadD threadD = new HasSelfPrivateNum.ThreadD(hasSelfPrivateNum);
        threadD.setName("D");
        threadC.start();
        threadD.start();
    }
}
