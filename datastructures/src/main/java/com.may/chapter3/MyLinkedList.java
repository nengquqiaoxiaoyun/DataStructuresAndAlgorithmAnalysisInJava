package com.may.chapter3;


public class MyLinkedList<T> {
    // 链表大小
    private int theSize;
    // 用于记录修改次数(add\remove)
    private int modCount;
    // 头结点 即第一个节点的前一个
    private Node<T> beginMarker;
    // 尾节点 即最后一个节点的后一个
    private Node<T> endMarker;

    // 构造
    public MyLinkedList() {
        doClear();
    }

    // 链表大小
    public int size() {
        return theSize;
    }

    // 清空链表
    public void clear() {
        doClear();
    }

    /**
     * 创建一个空表即只有头节点和尾节点的链表
     */
    private void doClear() {

        // 创建头节点
        beginMarker = new Node<>(null, null, null);
        // 创建尾节点
        endMarker = new Node<>(null, beginMarker, null);
        // 头节点的指向
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    // 链表是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 嵌套类的成员在外部类中是可见的
     * 由于是私有的嵌套类，成员属性无关紧要，因为无论是什么，外部类都可见
     *
     * @param <T>
     */
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

    // 删除指定索引的节点返回该节点的数据
    public T remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 把数据添加到最后
     *
     * @param element
     * @return
     */
    public boolean add(T element) {
        add(size(), element);
        return true;
    }

    /**
     * 把数据插入到指定位置的节点
     *
     * @param index   目标索引
     * @param element 数据
     */
    public void add(int index, T element) {
        addBefore(getNode(index, 0, size()), element);
    }

    /**
     * 拿到索引位置节点的数据
     *
     * @param index 目标索引
     * @return 节点的数据
     */
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * 把目标索引节点的元素改为目标元素
     *
     * @param index   目标索引
     * @param element 数据
     * @return 被修改的元素
     */
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        // 该值需要返回
        T old = node.data;
        node.data = element;
        return old;
    }

    /**
     * 插入元素到节点中
     * 对于新元素的插入 应该改变的是 新的元素原来的节点的上一个链，以及上一个节点的下一个链
     * 而不是对新元素进行操作
     *
     * @param node    插入到此节点的前面
     * @param element 数据
     */
    private void addBefore(Node<T> node, T element) {
        /**
         * 创建一个新的节点
         * 该节点位于上一个节点的下一个以及下一个节点的上一个
         * 对于修改节点的引用而言
         * 修改的应该是原来节点 而不是 对新节点进行操作
         */
        Node newNode = new Node<>(element, node.prev, node);
        // 修改上一个节点的下一个链接指向
        node.prev.next = newNode;
        // 修改原始节点的上一个链接指向
        node.prev = newNode;

        theSize++;
        modCount++;
    }

    /**
     * 删除目标节点
     * 对于删除而言 需要修改的目标节点的上下节点的引用
     *
     * @param node 需要删除的节点
     * @return 返回被删除的节点中的数据
     */
    private T remove(Node<T> node) {
        // 当前节点的上一个节点引用给下一个节点的上一个引用
        node.next.prev = node.prev;
        // 当前节点的下一个节点引用给上一个节点的下一个引用
        node.prev.next = node.next;
        theSize--;
        modCount++;
        return node.data;
    }

    /**
     * 拿到范围在 0 至 size() - 1  的节点
     *
     * @param index 目标索引
     * @return 索引对应的节点
     */
    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 查找位于索引位置的节点
     *
     * @param index 目标节点的索引
     * @param lower 最低有效索引
     * @param upper 最高有效索引
     * @return 索引对应的节点
     */
    private Node<T> getNode(int index, int lower, int upper) {

        Node<T> node;
        indexOutException(index, lower, upper);

        /**
         * 位于index的节点需要查找到该节点的上一个节点或者下一个节点的位置
         * 索引在总大小的一半之内 正向遍历 否则反向遍历
         */
        if (index < size() / 2) {
            node = beginMarker;
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = endMarker;
            for (int i = size(); i > index; i--) {
                node = node.prev;
            }
        }

        return node;

    }

    // 索引越界检查
    private void indexOutException(int index, int lower, int upper) {
        if (index < lower || index > upper)
            throw new IndexOutOfBoundsException();
    }

}
