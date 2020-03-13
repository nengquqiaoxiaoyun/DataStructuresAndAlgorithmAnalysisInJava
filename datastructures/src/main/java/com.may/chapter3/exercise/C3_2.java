package com.may.chapter3.exercise;

/**
 * 3.2 用单链表和双链表分别时间节点的交换
 *
 * @param <T>
 */
public class C3_2<T> {
    public static void main(String[] args) {

        // 空链表
        Node<Object> top = new Node<>(1, null, null);
        Node<Object> node1 = new Node<>("node1", top, null);
        Node<Object> node2 = new Node<>("node2", node1, null);
        Node<Object> node3 = new Node<>("node3", node2, null);
        Node<Object> node4 = new Node<>("node4", node3, null);
        Node<Object> tail = new Node<>(2, node3, null);
        top.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = tail;

        System.out.println("node2的下一个： " + node2.next.data);
        System.out.println("node2的上一个： " + node2.prev.data);
        System.out.println("node3的上一个： " + node3.prev.data);
        System.out.println("node3的下一个： " + node3.next.data);
        System.out.println("node4的上一个： " + node4.prev.data);
        System.out.println("node1的下一个： " + node1.next.data);
        System.out.println("swap === ");
        // 交换节点2和节点3
//        swapDoubleNodes(node2);
        // 交换节点3和节点4
        swapSingleNodes(node2);
        System.out.println("node2的下一个： " + node2.next.data);
        System.out.println("node2的上一个： " + node2.prev.data);
        System.out.println("node3的上一个： " + node3.prev.data);
        System.out.println("node3的下一个： " + node3.next.data);
        System.out.println("node4的上一个： " + node4.prev.data);
        System.out.println("node4的下一个： " + node4.next.data);
        System.out.println("node1的下一个： " + node1.next.data);
        System.out.println("swap === ");
    }

    private static class Node<T> {
        /**
         * @param data 节点中的数据
         * @param prev 上一个链表的引用
         * @param next 下一个链表的引用
         */
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public T data;
        public Node<T> prev;
        public Node<T> next;

    }

    /**
     * 使用双端链表交换相邻的节点
     *
     * @param p 需要交换的节点中的上一个节点
     */
    public static void swapDoubleNodes(Node p) {
        Node before, after;
        before = p.prev;
        after = p.next;


        p.next = after.next;
        p.prev = after;
        after.prev = before;
        after.next = p;

        before.next = after;
        p.next.prev = p;
    }

    /**
     * 使用单链表交换节点
     * （这里传入的Node为双端链表只需关注next就行）
     *
     * @param before 上一个节点
     */
    public static void swapSingleNodes(Node before) {

        Node current, after;
        current = before.next;
        after = current.next;

        before.next = after;
        current.next = after.next;
        after.next = current;
    }
}
