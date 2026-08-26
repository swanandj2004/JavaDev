package com.example.Spring_Security;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    User findByUsername(String username);
}
