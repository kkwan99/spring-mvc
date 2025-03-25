package com.example.springmvc.repository;

import com.example.springmvc.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemeoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return storage.containsKey(id);
    }
}
