package com.example.Auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
@RestController
@RequestMapping("/users")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class);
	}

	private UserRepository userRepository;
	public AuthApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository=userRepository;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body("User already exists");
		}
		if(userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body("User already exists");
		}
		userRepository.save(user);
		
		return ResponseEntity.ok("You have registered successfully");
	}
	
}
