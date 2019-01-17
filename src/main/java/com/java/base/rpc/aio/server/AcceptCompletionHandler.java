package com.java.base.rpc.aio.server;

import java.io.ObjectInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/17 18:39
 * @Version: 1.0
 **/
public class AcceptCompletionHandler implements CompletionHandler {
    @Override
    public void completed(Object result, Object attachment) {
        AsyncTimeServerHandler as = (AsyncTimeServerHandler) attachment;
        AsynchronousSocketChannel ass = (AsynchronousSocketChannel) result;
        as.asynchronousServerSocketChannel.accept(as, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ass.read(buffer, buffer, new ReadCompletionHandler(ass));


    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
        AsyncTimeServerHandler as = (AsyncTimeServerHandler) attachment;
        as.countDownLatch.countDown();
    }
}
