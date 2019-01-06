package com.java.base.thread.synchronizedMethod;

/**
 * Created by shihao 2018/3/23 13:58
 */
public class MyObject {

    synchronized public void methodA() throws InterruptedException {
        System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end endTime=" + System.currentTimeMillis());
    }

    synchronized public void methodB() throws InterruptedException {
        System.out.println("begin methodB threadName=" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end");
    }

    static class ThreadA extends Thread {
        private MyObject myObject;

        public ThreadA(MyObject myObject) {
            super();
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            try {
                myObject.methodA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        private MyObject myObject;

        public ThreadB(MyObject myObject) {
            super();
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            try {
                myObject.methodB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 多个线程下持有object的对象锁，其他线程可以异步调用object对象中的非synchronized 类型的方法
     *
     * @param args
     */
//    public static void main(String[] args) {
//        MyObject myObject = new MyObject();
//        ThreadA threadA = new ThreadA(myObject);
//        threadA.setName("A");
//        ThreadB threadB = new ThreadB(myObject);
//        threadB.setName("B");
//        threadA.start();
//        threadB.start();
//    }
//    public static void main(String[] args) throws InterruptedException {
//        PublicVar publicVar = new PublicVar();
//        Thread3 thread = new Thread3(publicVar);
//        thread.start();
//        Thread.sleep(200);
//        publicVar.getValue();
//
//    }


    /**
     * 脏读 getvalue()加上synchronize之后 可以避免脏读
     */
    static class PublicVar {
        public String username = "A";
        public String password = "AA";

        synchronized public void setValue(String username, String password) throws InterruptedException {
            this.username = username;
            Thread.sleep(4000);
            this.password = password;
            System.out.println("setValue method thread name=" + Thread.currentThread().getName() + "username = " + username + "password = " + password);
        }

        synchronized public void getValue() {
            System.out.println("getValue method thread name=" + Thread.currentThread().getName() + "username = " + username + "password = " + password);
        }
    }

    static class Thread3 extends Thread {
        private PublicVar publicVar;

        public Thread3(PublicVar publicVar) {
            this.publicVar = publicVar;
        }

        @Override
        public void run() {
            super.run();
            try {
                publicVar.setValue("B", "BB");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 可重入锁
     */
    static class Test {
        public int i = 10;

        synchronized public void operateIMainMethod() {
            try {
                i--;
                System.out.println("main print i=" + i);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Test {
        synchronized public void operateSubMethod() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("sub print i=" + i);
                    Thread.sleep(100);
                    this.operateIMainMethod();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Sub sub = new Sub();
            sub.operateSubMethod();
        }
    }


    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }


    /**
     * synchronized同步代码块,执行效率低，效果还是同步的
     */
    static class ObjectService {
        public void serviceMethod() {
            try {
                synchronized (this) {
                    System.out.println("begin time=" + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("end end=" + System.currentTimeMillis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread4 extends Thread {
        private ObjectService objectService;

        public Thread4(ObjectService objectService) {
            this.objectService = objectService;
        }

        @Override
        public void run() {
            super.run();
            objectService.serviceMethod();
        }
    }

    static class Thread5 extends Thread {
        private ObjectService objectService;

        public Thread5(ObjectService objectService) {
            this.objectService = objectService;
        }

        @Override
        public void run() {
            super.run();
            objectService.serviceMethod();
        }
    }

    static class Run {
        public static void main(String[] args) {
            ObjectService objectService = new ObjectService();
            Thread4 thread4 = new Thread4(objectService);
            thread4.setName("a");
            thread4.start();
            Thread5 thread5 = new Thread5(objectService);
            thread5.setName("b");
            thread5.start();
        }
    }


    static class Task {
        private String getData1;
        private String getData2;

        public void doLongTimeTask() {
            try {
                System.out.println("begin task");
                Thread.sleep(3000);
                String privateGetData1 = "1 threadName=" + Thread.currentThread().getName();
                String privateGetData2 = "2 threadName=" + Thread.currentThread().getName();
                synchronized (this) {
                    getData1 = privateGetData1;
                    getData2 = privateGetData2;
                }
                System.out.println(getData1);
                System.out.println(getData2);
                System.out.println("end task");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ObjectServices {
        public void serviceMethodA() {
            try {
                synchronized (this) {
                    System.out.println("A begin time = " + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("A end time = " + System.currentTimeMillis());
                }
            } catch (Exception e) {

            }
        }

        public void serviceMethodB() {
            synchronized (this) {
                System.out.println("B begin time =" + System.currentTimeMillis());
                System.out.println("B end time = " + System.currentTimeMillis());
            }
        }
    }

    static class Thread6 extends Thread {
        private ObjectServices objectServices;

        public Thread6(ObjectServices objectServices) {
            this.objectServices = objectServices;
        }

        @Override
        public void run() {
            super.run();
            objectServices.serviceMethodA();
        }
    }

    static class Thread7 extends Thread {
        private ObjectServices objectServices;

        public Thread7(ObjectServices objectServices) {
            this.objectServices = objectServices;
        }

        @Override
        public void run() {
            super.run();
            objectServices.serviceMethodB();
        }
    }

    static class Run1 {
        public static void main(String[] args) {
            ObjectServices objectServices = new ObjectServices();
            Thread6 thread6 = new Thread6(objectServices);
            thread6.setName("a");
            thread6.start();
            Thread7 thread7 = new Thread7(objectServices);
            thread7.setName("b");
            thread7.start();
        }
    }


    /**
     * synchronized 同步方法,锁非this对象 对象监视器，anyString必须是同一个对象，如果不是同一个对象，运行的结果就是异步调用了,就会交叉运行。
     */
    static class service2 {
        private String usernameParam;
        private String passwordParam;

        public void setUsernamePassword(String username, String password) throws InterruptedException {
            try {
                String anyString = new String();
                synchronized (anyString) {
                    System.out.println("线程名称" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步块");
                    Thread.sleep(3000);
                    passwordParam = password;
                    System.out.println("线程名称" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步块");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    static class Thread8 extends Thread {
        private service2 service2;

        public Thread8(service2 service2) {
            this.service2 = service2;
        }

        @Override
        public void run() {
            super.run();
            try {
                service2.setUsernamePassword("a", "aa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Thread9 extends Thread {
        private service2 service2;

        public Thread9(service2 service2) {
            this.service2 = service2;
        }

        @Override
        public void run() {
            super.run();
            try {
                service2.setUsernamePassword("b", "bb");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Run3 {
        public static void main(String[] args) {
            service2 service = new service2();
            Thread8 thread8 = new Thread8(service);
            thread8.setName("a");
            thread8.start();
            Thread9 thread9 = new Thread9(service);
            thread9.setName("b");
            thread9.start();
        }
    }


    /**
     * synchronized(非this对象)与同步synchronized方法是异步调用的效果,对象监视器不同 是异步的 会出现脏读的情况。
     */
    static class service4 {
        private String anyString = new String();

        public void a() {
            try {
                synchronized (anyString) {
                    System.out.println("a begin");
                    Thread.sleep(3000);
                    System.out.println("a end");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        synchronized public void b() {
            System.out.println("b begin");
            System.out.println("b end");
        }
    }

    static class Thread10 extends Thread {
        private service4 service4;

        public Thread10(service4 service4) {
            this.service4 = service4;
        }

        @Override
        public void run() {
            super.run();
                service4.a();
        }
    }

    static class Thread11 extends  Thread {
        private service4 service4;

        public Thread11(service4 service4) {
            this.service4 = service4;
        }

        @Override
        public void run() {
            super.run();
            service4.b();
        }
    }

    static class Run4{
        public static void main(String[] args) {
            service4 service4 = new service4();
            Thread10 thread10 = new Thread10(service4);
            thread10.setName("a");
            thread10.start();
            Thread11 thread11 = new Thread11(service4);
            thread11.setName("b");
            thread11.start();
        }
    }

}
