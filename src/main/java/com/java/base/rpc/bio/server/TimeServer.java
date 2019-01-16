package com.java.base.rpc.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: BIO时间服务器
 * @Author: shihao
 * @Date: 2019/1/16 14:30
 * @Version: 1.0
 **/
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(" The time server is start in port : " + port);
            Socket socket = null;
            //利用线程池,伪异步IO
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50, 1000);

            while (true) {
                socket = serverSocket.accept();
                executePool.execute(new TimeServerHandlers(socket));
                //new Thread(new TimeServerHandlers(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println(" The time server close!");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
