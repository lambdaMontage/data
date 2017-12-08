package com.springboot.xylj.common.queue;

/**
 * 自定义队列接口
 * Created by shihao 2017/9/20 17:20
 */
public interface CustomQueue<T> {

    //队列中添加元素 入队函数
    void enqueue(T data) throws Exception;

    //删除队列中元素 出队函数
    T dequeue() throws Exception;

    //验证是否为空
    boolean isEmpty() throws Exception;

    //获取队列长度
    int size() throws Exception;

    //获取队列中头元素
    T getFrontElement() throws Exception;
}
