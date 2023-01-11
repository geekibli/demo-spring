package com.example.testspring.jsonpath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JsonSmartJsonProvider;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        List<String> authors1 = JsonPath.read(JsonConst.store, "$..book[?(@.price <= $['expensive'])]");
        System.out.println(authors1);

    }
}
