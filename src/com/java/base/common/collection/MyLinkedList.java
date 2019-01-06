package com.java.base.common.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * LinkedList 泛型类的实现
 * Created by shihao on 2017/9/19 下午9:31
 */
public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;


    private static class Node<T> {
        public Node(T d, Node<T> p, Node<T> n) {
            data = d;
            prev = p;
            next = n;
        }

        public T data;
        public Node<T> prev;
        public Node<T> next;
    }

    public MyLinkedList() {
        clear();
    }

    /**
     * 创建并连接头节点和尾节点，然后设置大小为0;
     */
    public void clear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    private void add(int idx, T x) {
        addBefore(getNode(idx), x);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * 前后节点中间 插入元素
     *
     * @param p
     * @param x
     */
    private void addBefore(Node<T> p, T x) {
        //p和p.prev创建新节点
        Node<T> newNode = new Node<T>(x, p.prev, p);
        //头节点指向新节点
        newNode.prev.next = newNode;
        //尾节点指向新节点
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * 前后节点互连 删除元素
     * @param p
     * @return
     */
    private T remove(Node<T> p) {
        //右边头节点 互连
        p.next.prev = p.prev;
        //左边尾节点 互连  相当于删除元素
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    private Node<T> getNode(int idx) {
        Node<T> p;

        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        //指针索引 向后的方向遍历该链表
        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i<idx; i++){
                p = p.next;
            }
            //从终端开始往回走
        }else {
            p = endMarker;
            for(int i = size(); i > idx; i--){
                p = p.prev;
            }
        }
        return  p;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;


        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount){
                throw  new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (!okToRemove){
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }

}
