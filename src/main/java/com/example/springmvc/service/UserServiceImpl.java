package com.example.springmvc.service;

import com.example.springmvc.domain.User;
import com.example.springmvc.dto.UserLoginRequest;
import com.example.springmvc.dto.UserRegisterRequest;
import com.example.springmvc.repository.UserRepository;
import com.example.springmvc.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void register(UserRegisterRequest request) {
        if (userRepository.existsById(request.id())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }

        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(request.password(), BCrypt.gensalt());

        User user = new User();
        user.setId(request.id());
        user.setPassword(hashedPassword);
        user.setEmail(request.email());
        user.setNickname(request.nickname());
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }

    @Override
    public String login(UserLoginRequest request) {
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        if(!BCrypt.checkpw(request.password(), user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getId());
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
