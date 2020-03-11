package com.may.chapter3;

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

}
