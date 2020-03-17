package com.may.chapter3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 该类的细节
 * 1. 保持基础数组，数组的容量，以及当前容量
 * 2. 通过新数组，将老数组拷贝到新数组来改变数组的容量，允许虚拟机回收老数组
 * 3. 提供get\set
 * 4. 提供基本的方法实现 如：size、isEmpty、clear等
 * 5. 提供一个实现Iterator接口的类
 */
public class MyArrayList<T> implements Iterable<T> {

    // 默认容量10
    private static final int DEFAULT_CAPACITY = 10;
    // 数组容量
    private int theSize;
    // 数组
    private T[] theItems;

    // 构造函数 初始化容量为10
    public MyArrayList() {
        doClear();
    }

    // 清空数组
    public void clear() {
        doClear();
    }

    // 初始化数组容量 默认为10
    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    // 数组长度
    public int size() {
        return theSize;
    }

    // 判断数组是否有元素
    public boolean isEmpty() {
        return size() == 0;
    }

    // 把数组的容量调整为当前的size
    public void trimToSize() {
        ensureCapacity(size());
    }

    // 查找
    public T get(int index) {
        // 越界检查
        indexOutException(index);
        return theItems[index];
    }

    /**
     * 修改指定位置的值
     *
     * @param index  索引
     * @param newVal 要设置的值
     * @return 被修改的值
     */
    public T set(int index, T newVal) {
        // 越界检查
        indexOutException(index);
        // 索引所在位置的值 作用是为了返回原值
        T old = theItems[index];
        // 把索引所在位置的值设置为新值
        theItems[index] = newVal;
        // 返回被跟换的值
        return old;
    }

    /**
     * 当索引为负数或超过数组容量减一时发生异常
     */
    private void indexOutException(int index) {
        // 越界
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
    }


    // 扩容
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        // 原来的数组
        T[] oldArr = theItems;
        // 新数组
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = oldArr[i];
        }
    }


    // 将新元素添加到表尾
    public boolean add(T val) {
        add(size(), val);
        return true;
    }

    /**
     * 添加值到指定位置的索引
     *
     * @param index 目标索引
     * @param val   要增加的值
     */
    public void add(int index, T val) {
        // 添加可能容量溢出 需要扩容 扩容为原来的2倍 + 1
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);

        // 对于数组的添加 需要对index后面的整个数组移动
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = val;

        // 容量 + 1
        theSize++;
    }


    /**
     * 添加所有的元素到集合的末尾
     * @param items 需要添加的所有元素
     */
    public void addAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    /**
     * 从集合中删除所有包含的元素
     * @param items 需要删除的所有元素
     */
    public void removeAll(Iterable<? extends T> items) {
        Iterator<? extends T> needToRemoveIterator = items.iterator();
        if(!needToRemoveIterator.hasNext())
            return;

        // 集合中的所有元素
        Iterator<T> originalIterator = iterator();
        while (originalIterator.hasNext()) {
            T originalNext = originalIterator.next();
            // 需要删除的所有元素
            while(needToRemoveIterator.hasNext()) {
                T needToRemoveNext = needToRemoveIterator.next();
                if(originalNext.equals(needToRemoveNext))
                    originalIterator.remove();
            }
        }

    }


    /**
     * 删除指定索引的元素
     *
     * @param index 指定索引
     * @return 被删除的元素
     */
    public T remove(int index) {

        // 被删除的元素
        T old = theItems[index];
        // 对于数组的删除 删除中间的某个值需要整体向前移动
        for (int i = index; i <= size(); i++) {
            theItems[i] = theItems[i + 1];
        }
        // 删除后容量减少
        theSize--;
        return old;
    }


    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
