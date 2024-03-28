package com.spring.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class TestAuthController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/hello-secured")
    public String sayHelloSecured() {
        return "Hello secured";
    }
}
