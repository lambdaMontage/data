package com.java.base.rpc.bio.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/15 14:17
 * @Version: 1.0
 **/
public class RpcExporter {

    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostName, int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName, port));
        try {
            while (true) {
                executor.execute(new ExportTask(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }
}
