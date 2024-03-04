package com.example.spring_secutity_tutorial.repository;

import com.example.spring_secutity_tutorial.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
