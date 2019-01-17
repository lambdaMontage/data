package com.java.base.rpc.aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channel;
import java.nio.channels.CompletionHandler;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/17 18:56
 * @Version: 1.0
 **/
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel asynchronousSocketChannel;

    public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
        if (this.asynchronousSocketChannel == null)
            this.asynchronousSocketChannel = asynchronousSocketChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("The time server receive order :" + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void doWrite(String currentTime) {
        if (currentTime != null && currentTime.length() > 0) {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
            allocate.put(bytes);
            allocate.flip();
            asynchronousSocketChannel.write(allocate, allocate, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
