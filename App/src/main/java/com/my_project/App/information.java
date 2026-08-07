package com.my_project.App;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/student")
public class information {
    private Map<String,student>map = new HashMap<>();
    @GetMapping("/hello")
    public String sayHello() {
        return new String("Hello, there!");
    }
    @PostMapping("/post")
    public boolean createStudent(@RequestBody student s) {
        //TODO: process POST request
        map.put(s.getName(),s);
        return true;
    }
    @GetMapping("/get/{name}")
    public student studentID(@PathVariable String name) {
        return map.get(name);
    }
}
