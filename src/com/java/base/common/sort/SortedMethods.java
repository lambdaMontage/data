package com.java.base.common.sort;

/**
 * 排序算法实现
 * Created by shihao 2017/9/27 16:18
 */
public class SortedMethods<T extends Comparable<? super T>> {

    /**
     * 堆排序
     *
     * @param a 数组
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] a) {
        //构建堆
        for (int i = a.length / 2; i >= 0; i--) {
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            T temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            percDown(a, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child = 0;
        T npm;
        for (npm = a[i]; leftChild(i) < a.length; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child + 1].compareTo(a[child]) > 0) {
                child++;
            }
            if (npm.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = npm;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 插入排序
     *
     * @param a 数组
     * @return
     */
    public T[] insertionSort(T[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            T npm = a[p];
            for (j = p; j > 0 && npm.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = npm;
        }
        return a;
    }

    /**
     * 希尔排序
     *
     * @param a 数组
     * @return
     */
    private T[] shellSort(T[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T npm = a[i];
                for (j = i; j >= gap && npm.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = npm;
            }
        }
        return a;
    }


    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] npmArray, int left, int right) {
        if (left < right) {
            //找出索引
            int center = (left + right) / 2;
            //对左边数组递归
            mergeSort(a, npmArray, left, center);
            //对右边数组递归
            mergeSort(a, npmArray, center + 1, right);
            //合并
            merge(a, npmArray, left, center + 1, right);
        }
    }

    /**
     * @param a        比较的数组
     * @param npmArray 数组合并结果
     * @param leftPos  子数组最左边的索引
     * @param rightPos 右半部分开始的索引
     * @param rightEnd 子数组最右边的索引
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] npmArray, int leftPos, int rightPos, int rightEnd) {
        //左结尾节点
        int leftEnd = rightPos - 1;
        //数组合并后的节点
        int tmpPos = leftPos;
        //节点元素
        int numElements = rightEnd - leftPos + 1;

        //递归
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                npmArray[tmpPos++] = a[leftPos++];
            } else {
                npmArray[tmpPos++] = a[rightPos++];
            }
        }
        //复制左半部分到新的数组
        while (leftPos <= leftEnd) {
            npmArray[tmpPos++] = a[leftPos++];
        }
        //复制右半部分到新的数组
        while (rightPos <= rightEnd) {
            npmArray[tmpPos++] = a[rightPos++];
        }
        //将新数组copy到原数组
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = npmArray[rightEnd];
        }

    }

    /**
     * 归并排序算法
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] npmArray = (T[]) new Comparable[a.length];
        mergeSort(a, npmArray, 0, a.length - 1);
    }

    /**
     * 快速排序算法
     *
     * @param a   数组
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 三数中值分割法
     *
     * @param a     数组
     * @param left  左边索引
     * @param right 右边索引
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        } else if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        } else if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }
        swapReferences(a, center, right - 1);
        //返回枢纽
        return a[right - 1];
    }

    /**
     * 数组元素交换
     *
     * @param a      数组
     * @param left   左边元素
     * @param center 右边元素
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void swapReferences(T[] a, int left, int center) {
        T npm = a[left];
        a[left] = a[center];
        a[center] = npm;
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right) {
        //如果需要排序的数据大于3个则使用快排
        if (left + 3 <= right) {
            //得到枢纽的值
            T pivot = median3(a, left, right);
            int i = left;
            int j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }
            swapReferences(a, i, right - 1);
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else {
            SortedMethods sortedMethods = new SortedMethods();
            sortedMethods.insertionSort(a);
        }
    }

}
