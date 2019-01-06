package com.java.base.common.queue;

/**
 * 链表自定义队列
 * <p>
 * Created by shihao 2017/9/20 19:35
 */
public class CustomLinkedQueue<T> implements CustomQueue<T> {

    //头节点，尾节点
    private Node front;
    private Node rear;
    private int size;

    /**
     * 添加新节点到尾部
     *
     * @param data
     * @throws Exception
     */
    @Override
    public void enqueue(T data) throws Exception {
        Node newNode = new Node(data, null);
        if (isEmpty()) {
            front = newNode;
            //将新节点设置为尾节点
            rear = newNode;
        } else {
            //尾节点后面添加新节点
            rear.nexNode = newNode;
            //将新节点设置为尾节点
            rear = newNode;
        }

        size++;
    }

    /**
     * 移除队首元素
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
            //保存队首节点
            object = front.data;
            //将下一节点设置为队首节点
            front = front.nexNode;
        }
        size--;
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

    @Override
    public T getFrontElement() throws Exception {
        return front.data;
    }

    private class Node {
        T data;
        Node nexNode;

        public Node(Node nextNode) {
            this.nexNode = nextNode;
        }

        public Node(T data, Node nextNode) {
            this.data = data;
            this.nexNode = nextNode;
        }

    }
}
