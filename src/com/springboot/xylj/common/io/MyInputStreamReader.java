package com.springboot.xylj.common.io;

import sun.nio.cs.StreamDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * 自定义字节转字符流
 * <p>
 * Created by shihao 2017/10/20 13:20
 */
public class MyInputStreamReader extends Reader {

    private final StreamDecoder sd;

    public MyInputStreamReader(InputStream in) {
        super(in);
        try {
            sd = StreamDecoder.forInputStreamReader(in, this, (String) null);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public MyInputStreamReader(InputStream in ,String charsetName ) throws UnsupportedEncodingException {
        super(in);
        if (charsetName == null){
            throw new NullPointerException("charsetName");
        }
        sd = StreamDecoder.forInputStreamReader(in,this,charsetName);
    }


    public int read() throws IOException {
        return sd.read();
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
