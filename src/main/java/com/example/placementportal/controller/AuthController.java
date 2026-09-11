package com.example.placementportal.controller;

import com.example.placementportal.dto.AuthResponse;
import com.example.placementportal.dto.LoginRequest;
import com.example.placementportal.entity.User;
import com.example.placementportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}