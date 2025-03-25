package com.example.springmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 로그인 유효성 검사
public record UserLoginRequest(
        @NotBlank
        @Size(min = 6, max = 30)
        String id,

        @NotBlank
        @Size(min = 12, max = 50)
        String password
) {}
