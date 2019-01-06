package com.java.base.common.binaryTree;

/**
 * 左式堆
 * Created by shihao 2017/9/27 10:16
 */
public class LeftistHeap<T extends Comparable<? super T>> {
    //根节点
    private Node<T> root;

    public LeftistHeap() {
        root = null;
    }

    /**
     * 合并堆
     *
     * @param lsh 另一个左式堆
     */
    public void merge(LeftistHeap<T> lsh) {
        if (this == lsh) {
            return;
        }
        root = merge(root, lsh.root);
        lsh.root = null;
    }

    /**
     * 插入元素
     *
     * @param t
     */
    public void insert(T t) {
        root = merge(root, new Node<T>(t));
    }

    /**
     * 找出最小元素
     *
     * @return
     */
    public T finMin() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return root.element;
    }

    /**
     * 删除最小元素
     *
     * @return
     */
    public T deleteMin() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        //找到根元素
        T element = root.element;
        //合并左右子节点
        root = merge(root.left, root.right);
        return element;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    /**
     * 堆合并,判断过程
     *
     * @param h1 h1元素
     * @param h2 h2元素
     * @return
     */
    public Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        } else if (h2 == null) {
            return h1;
        } else if (h1.element.compareTo(h2.element) < 0) {
            return merge2(h1, h2);
        } else {
            return merge(h2, h1);
        }
    }

    /**
     * 合并左式堆 h1元素小于h2 h2于h1的右子堆合并
     *
     * @param h1
     * @param h2
     * @return
     */
    public Node<T> merge2(Node<T> h1, Node<T> h2) {
        if (h1.left == null) {
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if (h1.right.npl > h1.left.npl) {
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    /**
     * 交换左右儿子
     */
    public void swapChildren(Node<T> t) {
        Node<T> npm = t.right;
        t.right = t.left;
        t.left = npm;
    }

    /**
     * 打印堆中元素
     */
    public void print(Node n) {
        if (n == null) {
            return;
        }
        print(n.left);
        System.out.println(n.element + ":" + n.npl);
        print(n.right);
    }

    private static class Node<T> {
        //右子节点 、左子节点、节点中的元素、零路径长
        Node<T> right;
        Node<T> left;
        T element;
        int npl;

        Node(T theElement) {
            this(theElement, null, null);
        }

        public Node(T theElement, Node<T> lt, Node<T> rt) {
            element = theElement;
            right = rt;
            left = lt;
            npl = 0;

        }
    }
}
