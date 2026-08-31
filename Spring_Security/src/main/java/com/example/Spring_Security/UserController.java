package com.example.Spring_Security;

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
    private final RoleRepository roleRepository;

    public boolean userExists(String username, String email) {
        return userRepository.findByUsername(username) != null ||
            userRepository.findByEmail(email) != null;
    }

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/get/allusers")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/get/user/{username}")
    public User getUserDetails(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            return null;
        }

        return user;
    }

    @PostMapping("/create/role")
    public String createNewRole(@RequestBody Role role) {
        //TODO: process POST request
        roleRepository.save(role);
        
        return "New Role Created Successfully!";
    }
    
    
    @PostMapping("/create/user")
    public String createNewUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return "Password Can't Stay Empty. Enter your Password Please";
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User Already Exists. Try Again";
        }
        if (userRepository.existsByEmail(user.getEmail())) { 
            return "Email Already Exists. Try Again"; 
        }

        Role role = roleRepository .findByName("USER");
        if(role!=null) {
            user.setRole(role);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "New User Created Successfully!";
    }
    

    @PutMapping("/update/{username}")
    public User updateUserDetails(@PathVariable String username,@RequestBody User user) {

        User existingUser = userRepository.findByUsername(username);

        if(existingUser == null) {
            return null;
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(
            passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(existingUser);

        return existingUser;
    }

}
