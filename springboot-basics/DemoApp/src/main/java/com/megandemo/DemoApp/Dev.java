package com.megandemo.DemoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dev {

    @Autowired
    @Qualifier("laptop")
    Computer computer;

//    public Dev(Laptop laptop){
//        this.laptop = laptop;
//    }

//    @Autowired
//    public void setLaptop(Laptop laptop){
//        this.laptop = laptop;
//    }

    public void build() {

        computer.compile();


        System.out.println("Building the project...");
    }
}
