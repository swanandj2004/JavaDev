package com.example.Spring_Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@SpringBootApplication
@RestController
@RequestMapping("/path")
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@GetMapping("/get/hello")
	public String hello_String() {
		return "Hello !";
	}
	
	@GetMapping("/get/bye")
	public String bye_String() {
		return "Bye !";
	}
	
}
