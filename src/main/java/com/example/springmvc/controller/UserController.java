package com.example.springmvc.controller;

import com.example.springmvc.dto.UserLoginRequest;
import com.example.springmvc.dto.UserRegisterRequest;
import com.example.springmvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 요청 시 실행
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원 가입이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    // 로그인 요청 시 실행(토근 포함한 응답 반환)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody UserLoginRequest request) {
        String token = userService.login(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
