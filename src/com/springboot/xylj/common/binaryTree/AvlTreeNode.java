package com.springboot.xylj.common.binaryTree;

/**
 * Avl二叉查找树
 * Created by shihao 2017/9/21 16:13
 */
public class AvlTreeNode<AnyType extends Comparable<? super AnyType>> {


    int height;
    AnyType element;
    AvlTreeNode<AnyType> left;
    AvlTreeNode<AnyType> right;

    public AvlTreeNode(AnyType theElement) {
        this(theElement, null, null);
    }

    public AvlTreeNode(AnyType theElement, AvlTreeNode<AnyType> lt, AvlTreeNode<AnyType> rt) {
        element = theElement;
        left = lt;
        right = rt;
        height = 0;
    }

    private int height(AvlTreeNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }


    /**
     * Avl二叉树插入数据
     *
     * @param x
     * @param t
     * @return
     */
    private AvlTreeNode<AnyType> insert(AnyType x, AvlTreeNode<AnyType> t) {

        if (t == null) {
            return new AvlTreeNode<AnyType>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else ;//do nothing
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * Avl二叉树  单旋转 左->右
     *
     * @param k2
     * @return
     */
    private AvlTreeNode<AnyType> rotateWithLeftChild(AvlTreeNode<AnyType> k2) {
        AvlTreeNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height);
        return k1;
    }

    /**
     * Avl二叉树 双旋转
     *
     * @param k3
     * @return
     */
    private AvlTreeNode<AnyType> doubleWithLeftChild(AvlTreeNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3.left);
    }


    /**
     * Avl二叉树 单旋转 右->左
     *
     * @param k2
     * @return
     */
    private AvlTreeNode<AnyType> rotateWithRightChild(AvlTreeNode<AnyType> k2) {
        AvlTreeNode<AnyType> k1 = k2.left;
        k1.right = k2.left;
        k2 = k1.right;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.left), k1.height);
        return k2;
    }


}
