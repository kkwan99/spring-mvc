package com.example.springmvc.service;

import com.example.springmvc.dto.UserLoginRequest;
import com.example.springmvc.dto.UserRegisterRequest;

public interface UserService {
    void register(UserRegisterRequest request);
    String login(UserLoginRequest request);
    boolean existsById(String id);
}
