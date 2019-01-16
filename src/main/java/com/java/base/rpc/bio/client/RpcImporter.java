package com.java.base.rpc.bio.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description:
 * @Author: shihao
 * @Date: 2019/1/15 16:46
 * @Version: 1.0
 **/
public class RpcImporter<T> {
    public T importer(final Class<?> serviceClass, final InetSocketAddress addr) {
        T t = (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass.getInterfaces()[0]}, (proxy, method, args) -> {
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                socket = new Socket();
                socket.connect(addr);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF(serviceClass.getName());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);
                inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();
            } finally {
                if (socket != null) {
                    socket.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }

        });
        return t;
    }
}
