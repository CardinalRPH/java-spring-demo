package com.example.demojava.services;

import com.example.demojava.dto.LoginRequest;
import com.example.demojava.dto.RegisterRequest;
import com.example.demojava.models.User;
import com.example.demojava.repository.UserRepository;
import com.example.demojava.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;

    public   AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User created";
    }

    public Map<String , String>login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());

        Map<String, String> resData = new HashMap<>();
        resData.put("token", token);

        return resData;
    }
}
