package com.java.base.common.queue;

/**
 * 自定义队列
 * Created by shihao 2017/9/20 16:23
 */
public class CustomArrayQueue<T> implements CustomQueue<T> {

    private static int DEFAULT_SIZE = 10;
    private T array[] = null;
    //队首，队尾标注和队列的大小
    private int front;
    private int rear;
    private int size;

    public CustomArrayQueue() {
        array = (T[]) new Object[DEFAULT_SIZE];
        front = 0;
        rear = 0;
        size = 0;
    }

    public CustomArrayQueue(int DEFAULT_SIZE) {
        array = (T[]) new Object[DEFAULT_SIZE];
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * 入队
     *
     * @param data
     * @throws Exception
     */
    @Override
    public void enqueue(T data) throws Exception {
        if (size > 0 && front == rear) {
            throw new Exception("队列已!");
        }
        array[rear] = data;
        rear = (rear + 1) % (array.length);
        size++;
    }

    /**
     * 出队
     *
     * @return
     * @throws Exception
     */
    @Override
    public T dequeue() throws Exception {
        T object = null;
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            //得到队列头元素,并置为null
            object = array[front];
            array[front] = null;
            front = (front + 1) % (array.length);
            size--;
        }

        return object;
    }

    @Override
    public boolean isEmpty() throws Exception {
        return size() == 0;
    }

    @Override
    public int size() throws Exception {
        return size;
    }

    /**
     * 得到队列头元素
     *
     * @return
     * @throws Exception
     */
    @Override
    public T getFrontElement() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        } else {
            return array[front];
        }
    }
}
