package com.java.base.rpc.aio.client;


/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/17 19:23
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
        new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"AIO-AsyncTimeClientHandler-001").start();
    }
}
