package com.example.Spring_Security;
import java.util.List;
public class UserServiceImpl implements UserService {
    UserRepository userRepo;
    RoleRepository roleRepo;

    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }
    public User saveUser(User user) {
        userRepo.save(user);
        return user;
    }
    public List<User>getUsers() {
        return userRepo.findAll();
    }
    public Role saveRole(Role role) {
        roleRepo.save(role);
        return role;
    }
    public void addRoleToUser(String username, String name) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(name);
    }
}
