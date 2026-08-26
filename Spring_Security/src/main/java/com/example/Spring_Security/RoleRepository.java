package com.example.Spring_Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
