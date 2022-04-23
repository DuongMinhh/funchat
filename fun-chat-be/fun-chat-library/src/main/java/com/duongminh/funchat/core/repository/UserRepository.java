package com.duongminh.funchat.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duongminh.funchat.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    
    @Query(value = "SELECT u.* FROM {h-schema}users u WHERE LOWER(u.name) LIKE CONCAT('%', :search, '%') OR LOWER(u.email) LIKE CONCAT('%', :search, '%')", nativeQuery = true)
    List<User> findAllByNameOrEmail(@Param("search") String searchText);
    
}
