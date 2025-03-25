package com.example.springmvc.repository;

import com.example.springmvc.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String id);
    boolean existsById(String id);
}
