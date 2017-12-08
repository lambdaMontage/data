package com.springboot.xylj.common.hashTable;

/**
 * hash表项单元数组实现hash表
 * Created by shihao 2017/9/25 13:37
 */
public class QuadraticProbingHashTable<AnyType> {

    private static final int DEFAUL_TABLE_SIZE = 11;
    /*
       三种情况:
       1:null
       2:非null，且该项isActive
       3:非null,且该项标记被删除

     */
    private HashEntry<AnyType>[] array;
    //表单数据
    private int currentSize;


    public QuadraticProbingHashTable() {
        this(DEFAUL_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
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

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * Hash表中是否包含此元素
     *
     * @param x 查询元素
     * @return 查询结果
     */
    public boolean containts(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * 向Hash表中插入元素，若存在则不操作
     *
     * @param x 插入元素
     */
    public void insert(AnyType x) {
        int currentPos = findPos(x);
        System.out.println(x + "hash->:" + currentPos);
        if (isActive(currentPos)) {
            return;
        }
        array[currentPos] = new HashEntry<AnyType>(x, true);
        if (++currentSize > array.length / 2) {
            rehash();
        }
    }

    /**
     * 向Hash表中懒惰删除元素
     *
     * @param x
     */
    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    /**
     * 检查指定下标是否存在活动项
     *
     * @param currentPos 下标
     * @return 是否有活动项
     */
    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * 通过Hash算法找到某元素下标（平方探测法:） f(i) = f(i-1)+2i-1
     *
     * @param x 查找元素
     * @return 元素在数组中的下标
     */
    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myHash(x);
        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos > array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }

    /**
     * 对分离列表的再散列函数 插入空间过半时执行
     */
    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && oldArray[i].isActive) {
                insert(oldArray[i].element);
            }
        }
        System.out.println("\nrehash by length of: " + array.length);
    }

    /**
     * 简单的Hash算法
     *
     * @param x 元素
     * @return hash值
     */
    private int myHash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }
        return hashVal;
    }

    /**
     * 打印Hash表数据
     */
    public void printTable() {
        for (int i = 0; i < array.length; i++) {
            System.out.println("-----");
            if (array[i] != null) {
                System.out.println(array[i].element + " ");
            } else {
                System.out.println();
            }
        }
    }

    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType x) {
            this(x, true);
        }

        public HashEntry(AnyType x, boolean b) {
            element = x;
            isActive = b;
        }
    }

}
