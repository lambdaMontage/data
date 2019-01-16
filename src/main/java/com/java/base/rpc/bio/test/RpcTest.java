package com.java.base.rpc.bio.test;


import com.java.base.rpc.bio.client.RpcImporter;
import com.java.base.rpc.bio.server.EchoService;
import com.java.base.rpc.bio.server.EchoServiceImpl;
import com.java.base.rpc.bio.server.RpcExporter;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Description: BIO rpc test
 * @Author: shihao
 * @Date: 2019/1/15 16:58
 * @Version: 1.0
 **/
public class RpcTest {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost", 8088);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        RpcImporter<EchoService> echoServiceRpcImporter = new RpcImporter<>();
        EchoService localhost = echoServiceRpcImporter.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(localhost.echo("are you ok?"));
    }
}
