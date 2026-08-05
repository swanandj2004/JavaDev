package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return new String("Hello!");
    }
    @GetMapping("bye")
    public String bye() {
        return new String("Bye!");
    }
}
