package com.example.Spring_Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@RestController
@RequestMapping("/app")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean userExists(String username, String email) {
        return userRepository.findByUsername(username) != null ||
            userRepository.findByEmail(email) != null;
    }

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/get/allusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/user/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be empty");
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) { 
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists"); 
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }
    

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUserDetails(@PathVariable String username,@RequestBody User user) {

        User existingUser = userRepository.findByUsername(username);

        if(existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(
            passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }

}
