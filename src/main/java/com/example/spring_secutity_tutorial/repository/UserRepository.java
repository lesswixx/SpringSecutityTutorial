package com.example.spring_secutity_tutorial.repository;

import com.example.spring_secutity_tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
