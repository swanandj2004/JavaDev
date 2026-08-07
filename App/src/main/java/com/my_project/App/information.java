package com.my_project.App;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/student")
public class information {
    private Map<String,student>map = new HashMap<>();
    private Map<Long, student>map_id = new HashMap<>();
    private Map<Long, student>map_age = new HashMap<>();
    private Map<String, student>map_course = new HashMap<>();
    @GetMapping("/hello")
    public String sayHello() {
        return new String("Hello, there!");
    }
    @PostMapping("/post")
    public boolean createStudent(@RequestBody student s) {
        //TODO: process POST request
        map.put(s.getName(),s);
        map_id.put(s.getId(),s);
        map_age.put(s.getAge(), s);
        map_course.put(s.getCourse(), s);
        return true;
    }
    @GetMapping("/get/id/{id}")
    public student studentId(@PathVariable Long id) {
        return map_id.get(id);
    }
    @GetMapping("/get/name/{name}")
    public student studentName(@PathVariable String name) {
        return map.get(name);
    }
    @GetMapping("/get/age/{age}")
    public student studentAge(@PathVariable Long age) {
        return map_age.get(age);
    }
    @GetMapping("/get/course/{course}")
    public student studentCourse(@PathVariable String course) {
        return map_course.get(course);
    }
}
