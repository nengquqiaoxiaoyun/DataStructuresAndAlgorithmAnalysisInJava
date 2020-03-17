package com.may.chapter3.exercise;

import lombok.Data;

@Data
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
