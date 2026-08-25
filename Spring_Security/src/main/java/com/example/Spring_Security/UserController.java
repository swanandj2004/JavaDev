package com.example.Spring_Security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class UserController {
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/user/register")
    public String registerUser(@RequestBody User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            return "User Already Exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Created Successfully!";
    }

    @GetMapping("/get/page")
    public String getPage() {
        return new String("Hello !");
    }
    
    
}
