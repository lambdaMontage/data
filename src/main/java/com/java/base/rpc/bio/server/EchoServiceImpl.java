package com.java.base.rpc.bio.server;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/15 14:15
 * @Version: 1.0
 **/
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> I am ok " : "I am ok";

    }
}
