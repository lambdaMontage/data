package com.java.base.common.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList的实现 （数组实现）
 * Created by shihao on 2017/9/19 下午8:54
 */
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        clear();
    }

    private void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    /**
     * get方法
     *
     * @param idx
     * @return
     */
    public T get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    /**
     * set方法
     * @param idx
     * @param newVal
     * @return
     */
    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    /**
     * 数组容量
     * @param newCapacity
     */
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    /**
     * 添加元素
     * @param idx
     * @param x
     */
    private void add(int idx, T x) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        //元素添加到数组中 数组大小+1
        theItems[idx] = x;
        theSize++;
    }

    /**
     * 删除元素
     * @param idx
     * @return
     */
    private T remove(int idx){
        T removedItem = theItems[idx];
        for (int i = idx; i<size() -1 ; i++){
            theItems[i] = theItems[i+1];
        }
        //数组长度-1
        theSize--;
        return removedItem;
    }

    /**
     * 迭代器
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements  Iterator<T>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current<size();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw  new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
