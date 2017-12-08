package com.springboot.xylj.common.binaryTree;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树
 * <p>
 * Created by shihao on 2017/9/20 下午11:49
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * 递归检查二叉树中是否包含这个元素。
     *
     * @param x
     * @param t
     * @return
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);

        //递归检查数据。
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            //相等返回 true
            return true;
        }

    }

    /**
     * 递归检查二叉树中最小元素的引用
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * 非递归检查二叉树中最大元素引用
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 在二叉树中插入元素
     * @param x 元素
     * @param t 节点
     * @return
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<AnyType>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else ;//do nothing
        return t;
    }

    /**
     * 二叉树中删除元素
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return t;
        }
        int compareResult = x.compareTo(t.element);

        //具有一个儿子节点 递归删除
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        }
        //找出右节点最小数据 代替该节点并递归删除数据
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    /**
     * 顺序打印二叉树查找
     * @param t
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * 后序遍历计算
     *
     * @return
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null) {
            return -1;
        } else {
            return Math.max(height(t.left), height(t.right)) + 1;
        }
    }

    private static class BinaryNode<AnyType> {

        //节点上的元素 左节点 右节点
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {

            element = theElement;
            left = lt;
            right = rt;
        }
    }
}
