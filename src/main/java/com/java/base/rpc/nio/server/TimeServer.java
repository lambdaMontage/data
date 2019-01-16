package com.java.base.rpc.nio.server;


/**
 * @Description: NIO时间客户端
 * @Author: shihao
 * @Date: 2019/1/16 16:54
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
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer, "NIO-port").start();
    }
}
