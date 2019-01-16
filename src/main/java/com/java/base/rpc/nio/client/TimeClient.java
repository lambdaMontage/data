package com.java.base.rpc.nio.client;


/**
 * @Description: nio客户端
 * @Author: shihao
 * @Date: 2019/1/16 10:15
 * @Version: 1.0
 **/
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new Thread(new TimeClientHandler("127.0.0.1", port), "TimeClient-001").start();
    }
}
