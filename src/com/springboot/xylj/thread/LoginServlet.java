package com.springboot.xylj.thread;

/**
 * 模拟servlet组件
 * Created by shihao 2018/3/12 10:14
 */
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;


    /**
     * 这个类是典型的非线程安全的。加入synchronized会解决这个问题，排队进入该方法
     * @param username
     * @param password
     */
    public synchronized static void dopost(String username, String password) {
        try {
            usernameRef = username;
            if (username.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username= " + usernameRef + "password= " + passwordRef);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ALogin extends Thread{
        @Override
        public void run() {
            LoginServlet.dopost("a","aa");
        }
    }

    static class BLogin extends Thread{
        @Override
        public void run() {
            LoginServlet.dopost("b","bb");
        }
    }

    static class Run{
        public static void main(String[] args) {
            ALogin aLogin = new ALogin();
            aLogin.start();
            BLogin bLogin = new BLogin();
            bLogin.start();
        }
    }
}
