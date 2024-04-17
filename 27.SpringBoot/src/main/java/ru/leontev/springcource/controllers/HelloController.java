package ru.leontev.springcource.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Value("${say}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(hello);
        return "hello";
    }
}
