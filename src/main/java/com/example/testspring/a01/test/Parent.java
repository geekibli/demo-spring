package com.example.testspring.a01.test;

import lombok.Data;


public class Parent {

    private String name;

    public String getName() {
        System.out.println("sdjkalsjdlsakjlkdsjl....");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
