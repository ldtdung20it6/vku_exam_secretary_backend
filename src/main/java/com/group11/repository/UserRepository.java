package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{    
    public User findByEmail(String email);
}
