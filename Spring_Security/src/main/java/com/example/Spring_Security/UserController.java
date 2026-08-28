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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;





@RestController
@RequestMapping("/app")
public class UserController {
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public boolean userExists(String username, String email) {
        User temp = userRepository.findByUsername(username);
        temp = userRepository.findByEmail(email);
        if(temp!=null) {
            return true;
        }
        return false;
    }

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/get/allusers")
    public ResponseEntity<List<User>> getAllUsers(@PathVariable String username) {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/user/{username}")
    public ResponseEntity<User> getUserDetails(@RequestParam String param, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if(user==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    
    @PostMapping("path")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername())!=null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) { 
            return ResponseEntity .status(HttpStatus.CONFLICT) .body("Email Already Exists"); 
        }
        
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return ResponseEntity .status(HttpStatus.CREATED) .body("User Created Successfully");
    }
    

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUserDetails(@PathVariable String username, @RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser==null) {
            return ResponseEntity.notFound().build();
        }
        User updatedUser = new User();
        updatedUser.setUsername(updatedUser.getUsername());
        updatedUser.setEmail(updatedUser.getEmail());
        updatedUser.setPassword(updatedUser.getPassword());
        
        return ResponseEntity.ok(updatedUser);
    }

}
