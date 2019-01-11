package com.java.base.common.binaryTree;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

/**
 * 优先队列（堆） 二叉完全树
 * Created by shihao 2017/9/26 13:30
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    //默认容量
    private static final int DEFAULT_CAPACITY = 10;
    //堆中元素数量
    private int currentSize;
    //堆数组
    private T[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity];
    }


    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    /**
     * 堆中插入元素 上虑
     *
     * @param t 元素
     */
    public void insert(T t) {
        if (array.length - 1 == currentSize) {
            enLargeArray(array.length * 2 + 1);
        }
        //添加空穴
        int hole = ++currentSize;
        //空穴<父元素 交换两者位置。
        for (; hole > 0 && array[hole].compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = t;
    }


    public T findMin() {
        return array[1];
    }

    /**
     * 找到并删除最小元素
     *
     * @return
     */
    public T deleteMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        //移走根
        T minItem = findMin();
        //把最后一个节点移到根的位置
        array[1] = array[currentSize--];
        //向下筛选节点
        percolateDown(1);
        return minItem;
    }

    /**
     * 对最小元素下虑
     *
     * @param hole
     */
    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            //找出 左右子节点中较小的元素。
            if (child != currentSize && array[child].compareTo(array[child + 1]) > 0) {
                child++;
            }
            //空穴元素和子节点比较。大于位置替换
            if (array[hole].compareTo(array[child]) > 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * 数组容量加大
     *
     * @param newSize
     */
    private void enLargeArray(int newSize) {
        ArrayList<T> newArray = new ArrayList<>(newSize);
        for (int i = 0; i < currentSize; i++) {
            newArray.add(array[i]);
        }
        array = (T[]) newArray.toArray();
    }


    /**
     * 堆构建
     */
    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }

    }


}
