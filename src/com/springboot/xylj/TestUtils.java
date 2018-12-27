package com.springboot.xylj;

import com.springboot.xylj.common.binaryTree.LeftistHeap;
import com.springboot.xylj.common.collection.MyArrayList;
import com.springboot.xylj.common.collection.MyLinkedList;
import com.springboot.xylj.common.collection.QuadraticProbingHashTable;
import com.springboot.xylj.common.collection.SeparateChainingHashTable;
import com.springboot.xylj.common.queue.CustomArrayQueue;
import com.springboot.xylj.common.queue.CustomLinkedQueue;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by shihao 2017/8/21 11:30
 */
public class TestUtils {


    /**
     * 集合测试类
     */
    @Test
    public void test() {
//        String str = "2016-02-13 11:34";
//        LocalDateTime ldt;
//        try {
//            ldt = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
//        } catch (IllegalArgumentException e) {
//            ldt = LocalDateTime.parse(str,DateTimeFormatter.ofPattern("YYYY-MM-dd"));
//        }
//        System.out.println(ldt);
        System.out.println("dgsgh");

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add("vue.js");
        myArrayList.add("java");
        myArrayList.add("mySql");
        Iterator iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            System.out.println(next);
        }

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        Iterator iterator1 = myLinkedList.iterator();
        while (iterator1.hasNext()) {
            int nextInt = (int) iterator1.next();
            System.out.println(nextInt);
        }
    }


    /**
     * 队列测试类
     */
    @Test
    public void queueTest() {

        //队列数组实现
        CustomArrayQueue<String> stringCustomeArrayQueue = new CustomArrayQueue<>(4);
        try {
            System.out.println("队列是否为空:" + stringCustomeArrayQueue.isEmpty());
            stringCustomeArrayQueue.enqueue("java");
            stringCustomeArrayQueue.enqueue("javaScript");
            stringCustomeArrayQueue.enqueue("vue.js");
            stringCustomeArrayQueue.enqueue("oracle");
            System.out.println("队首元素:" + stringCustomeArrayQueue.getFrontElement());
            System.out.println("队列长度:" + stringCustomeArrayQueue.size());

            while (!stringCustomeArrayQueue.isEmpty()) {
                System.out.println("移除第:" + stringCustomeArrayQueue.dequeue());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //队列链表实现
        CustomLinkedQueue<String> objectCustomLinkedQueue = new CustomLinkedQueue<String>();
        try {
            System.out.println("队列是否为空:" + objectCustomLinkedQueue.isEmpty());
            objectCustomLinkedQueue.enqueue("java");
            objectCustomLinkedQueue.enqueue("javaScript");
            objectCustomLinkedQueue.enqueue("vue.js");
            objectCustomLinkedQueue.enqueue("oracle");
            objectCustomLinkedQueue.enqueue("Mysql");
            System.out.println("队首元素:" + objectCustomLinkedQueue.getFrontElement());
            System.out.println("队列长度:" + objectCustomLinkedQueue.size());

            while (!objectCustomLinkedQueue.isEmpty()) {
                System.out.println("移除第:" + objectCustomLinkedQueue.dequeue());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 分离链接散列表测试
     */
    @Test
    public void HashTableTest() {
        Random random = new Random();
        SeparateChainingHashTable<Integer> integerSeparateChainingHashTable = new SeparateChainingHashTable<>();
        for (int i = 0; i < 30; i++) {
            integerSeparateChainingHashTable.insert(random.nextInt(30));
        }
        integerSeparateChainingHashTable.printTable();
    }

    /**
     * 平方探测法的散列实现
     */
    @Test
    public void QuadraticHashTableTest() {
        Random random = new Random();
        QuadraticProbingHashTable<Integer> integerQuadraticProbingHashTable = new QuadraticProbingHashTable<>();
        for (int i = 0; i < 20; i++) {
            integerQuadraticProbingHashTable.insert(random.nextInt(60));
        }
        integerQuadraticProbingHashTable.printTable();
    }

    /**
     * 左式堆实现
     */
    @Test
    public void LeftistHeapTest() {
        int numItems = 100;
        LeftistHeap<Integer> h = new LeftistHeap<>();
        LeftistHeap<Integer> h1 = new LeftistHeap<>();
        int i = 37;
        for (i = 37; i != 0; i = (i + 37) % numItems) {
            if (i % 2 == 0) {
                h1.insert(i);
            } else {
                h.insert(i);
            }
        }

        h.merge(h1);
        for (i = 1; i < numItems; i++) {
            if (h.deleteMin() != i) {
                System.out.println("Oops! " + i);
            }
        }
    }

    public volatile static int a = 0;


    static class testThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                a++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testThread testThread = new testThread();
        testThread testThread1 = new testThread();
        testThread.start();
        testThread1.start();
        // join 等待线程结束
        testThread.join();
        testThread1.join();
        System.out.println(a);
    }

}
