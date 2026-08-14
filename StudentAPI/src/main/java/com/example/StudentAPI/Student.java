package com.example.StudentAPI;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private Long id;

    private String firstname;
    private String lastname;
    private String course;

    public Student() {
    }

    public Student(Long id, String firstname, String lastname, String course) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCourse() {
        return course;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}