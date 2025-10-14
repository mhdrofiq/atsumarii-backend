package com.raroki.atsumarii.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Check if email exists (used for validation in createUser and updateUser)
    boolean existsByEmail(String email);
    
    // Check if username exists (used for validation in createUser and updateUser)
    boolean existsByUsername(String username);
}