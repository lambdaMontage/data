package com.springboot.xylj.common.hashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * hash表
 * Created by shihao 2017/9/22 16:04
 */
public class SeparateChainingHashTable<AnyType> {

    //默认容量、hash表的数组、当前数据个数
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;


    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[size];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    /**
     * 检查某整数为素数
     *
     * @param num 检查整数
     * @return 检查正数
     */
    private static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        if (num == 1 || num % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回不小于某个整数的素数
     *
     * @param num 整数
     * @return 下一个素数
     */
    private static int nextPrime(int num) {
        if (num == 0 || num == 1 || num == 2) {
            return 2;
        }
        if (num % 2 == 0) {
            num++;
        }
        while (!isPrime(num)) {
            num += 2;
        }
        return num;
    }

    /**
     * hash表中插入某元素，如若存在则不操作
     *
     * @param x 插入元素
     */
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);
            if (++currentSize > theLists.length) {
                rehash();
            }
        }

    }

    /**
     * hash表删除数据,如若不存在则不操作
     *
     * @param x 删除元素
     */
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }

    }

    /**
     * hash表是否包含某元素
     *
     * @param x 查询元素
     * @return 查询结果
     */
    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    /**
     * hash表置空
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        this.currentSize = 0;

    }

    /**
     * 再散列函数，插入空间不够时执行
     */
    private void rehash() {
        List<AnyType>[] oldLists = this.theLists;
        //新建一个为空的2倍表
        theLists = new List[theLists.length * 2];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<AnyType>();
        }

        //复制表插入元素
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (AnyType item : oldLists[i]) {
                insert(item);
            }
        }
    }

    /**
     * Hash算法
     *
     * @param x 元素
     * @return Hash值
     */
    private int myhash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    /**
     * 输出Hash表
     */
    public void printTable() {
        for (int i = 0; i < theLists.length; i++) {
            System.out.println("-----");
            Iterator iterator = theLists[i].iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
    }
}
