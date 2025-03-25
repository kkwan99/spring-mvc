package com.example.springmvc.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class User {
    private String id;
    private String password;
    private String email;
    private String nickname;
    private Instant createdAt;
}
