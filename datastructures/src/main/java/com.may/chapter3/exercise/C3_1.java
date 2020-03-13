package com.may.chapter3.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 3.1
 * 给定表L和P，他们包含以升序排列的整数
 * 将P中的元素作为L中的指定位置并打印
 * 这题看了答案不知道是不是我理解错了 附上答案参考
 */
public class C3_1 {

    // 表L
    public static void main(String[] args) {
        java.util.List<String> L = new ArrayList();

        Collections.addAll(L, "1", "2", "3", "4", "5");
        java.util.List<Integer> P = new ArrayList<>();
        Collections.addAll(P, 0, 2, 3);

        printLots(L, P);
    }

    private static <T> void printLots(java.util.List<T> l, java.util.List<Integer> p) {
        Iterator<Integer> iterator = p.iterator();
        while (iterator.hasNext())
            System.out.println(l.get(iterator.next()));


    }

    /*
    public static <T> void printLots(List<T> L, List<Integer> P) {
        Iterator<T> iterL = L.iterator();
        Iterator<Integer> iterP = P.iterator();
        T itemL = null;
        Integer itemP = 0;
        int start = 0;
        while (iterL.hasNext() && iterP.hasNext()) {
            itemP = iterP.next();
            System.out.println("Looking for position " + itemP);
            while (start < itemP && iterL.hasNext()) {
                start++;
                itemL = iterL.next();
            }
            System.out.println(itemL);
        }
  }
     */
}
