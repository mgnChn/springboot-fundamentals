package com.megandemo.springboot_rest_demo.Controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @RequestMapping("/{name}")
    public String sayHello(@PathVariable("name") String name){
        return "Hello " + name;
    }
}
