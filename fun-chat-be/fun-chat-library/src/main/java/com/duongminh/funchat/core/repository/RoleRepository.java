package com.duongminh.funchat.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duongminh.funchat.core.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    
}
