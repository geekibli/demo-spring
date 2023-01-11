package com.example.testspring.other;

import com.alibaba.fastjson.*;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;

import java.util.Arrays;

public class JsonTest {



    @Data
    static class Person {
        private String name;
        private String address;
        private Person son;
    }


    public static void main(String[] args) {

        Person father = new Person();
        father.setName("john");
        father.setAddress("beijign");
        Person son = new Person();
        son.setAddress("shanghai");
        son.setName("aili");
        father.setSon(son);

        String jsonString = JSON.toJSONString(son);
        System.err.println("jsonString" + jsonString);
//
        Object eval = JSONPath.eval(father, "$.son.name");
        System.out.println(eval);
    }






}
