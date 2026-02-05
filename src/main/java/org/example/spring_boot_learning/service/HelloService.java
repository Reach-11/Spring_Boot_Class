package org.example.spring_boot_learning.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String Getname(String name){
        if (name==null || name.isBlank()){
            return "Hello, Spring Boot Learning Service";
        }
        return "Hello, " + name + "!";
    }
}
