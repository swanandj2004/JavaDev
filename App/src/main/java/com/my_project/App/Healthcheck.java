package com.my_project.App;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/employee")
public class Healthcheck {
    private Map<Long, Employee> employee_id = new HashMap<>();
    private Map<String, Employee> employee_name = new HashMap<>();
    private Map<String, Employee> employee_dept = new HashMap<>();
    @GetMapping("/health-check")
    public String healthcheck() {
        return new String("Okay");
    }
    @PostMapping("/post")
    public void createEntry(@RequestBody Employee employee) {
        //TODO: process POST request
        employee_id.put(employee.getId(),employee);
        employee_name.put(employee.getName(),employee);
        employee_dept.put(employee.getDepartment(),employee);   
    }
    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employee_id.get(id);
    } 
}
