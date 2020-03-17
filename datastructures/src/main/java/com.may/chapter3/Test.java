package com.may.chapter3;

import com.may.chapter3.exercise.Person;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if ((next & 1) == 0) {
                iterator.remove();
            }
        }

        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("=== === ===");

        list.add(1, 11);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("=== === ===");

        list.remove(1);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("=== === ===");

        list.set(1, 15);
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    @org.junit.Test
    public void testLinked() {

        Person person1 = new Person("张三", 10);
        Person person2 = new Person("张三", 20);
        Person person3 = new Person("李四", 10);
        Person person4 = new Person("李四", 20);
        Person person5 = new Person("张三", 10);



        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(person1);
        myLinkedList.add(person2);
        myLinkedList.add(person3);
        myLinkedList.add(person4);

        for (Object o : myLinkedList) {
            System.out.println(o);
        }
        System.out.println(myLinkedList.contains(person5));
        System.out.println("=== === ===");

        Iterator iterator = myLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }

        System.out.println("=== === ===");
        for (Object o : myLinkedList) {
            System.out.println(o);
        }

        System.out.println("=== === ===");
    }

}
