package com.duongminh.funchat.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duongminh.funchat.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    
}
