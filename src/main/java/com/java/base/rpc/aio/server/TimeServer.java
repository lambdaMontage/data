package com.java.base.rpc.aio.server;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/17 18:31
 * @Version: 1.0
 **/
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler,"AIO-TimeServerHandler-001").start();

    }
}
