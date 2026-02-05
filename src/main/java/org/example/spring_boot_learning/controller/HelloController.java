package org.example.spring_boot_learning.controller;

import org.example.spring_boot_learning.dto.ApiStatus;
import org.example.spring_boot_learning.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
public class HelloController {
    // Constructor Injection
    private final HelloService helloService;
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    //@RequestParam to get query parameter "name" and required = false makes it optional
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        return helloService.Getname(name);
    }

    @GetMapping("/status")
    public ApiStatus status() {
        return new ApiStatus("Hello Spring Boot Learning Controller", 200, "OK");
    }

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppVersion() {
        return "Application Version: " + appVersion;
    }
}
