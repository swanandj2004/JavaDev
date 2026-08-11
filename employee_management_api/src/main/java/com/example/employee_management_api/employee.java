package com.example.employee_management_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class employee {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String gender;
    private Long age;
    private String department;

    public employee() { }

    public employee(Long id, String firstname, String lastname, String gender, Long age, String dept) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.department = dept;
    }
    
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public Long getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDept() {
        return department;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setDept(String dept) {
        this.department = dept;
    }
}
