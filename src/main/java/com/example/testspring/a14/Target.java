package com.example.testspring.a14;

public class Target {

    public void save(){
        System.out.println("Target#save ...");
    }

    public void save(int i){
        System.out.println("Target#save ... int: " + i  );
    }

    public void save(long l){
        System.out.println("Target#save ... long: " + l);
    }

}
