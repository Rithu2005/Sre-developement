package com.example.SRE.DEVLOPE.controller;

import com.example.SRE.DEVLOPE.dto.AuthResponse;
import com.example.SRE.DEVLOPE.dto.LoginRequest;
import com.example.SRE.DEVLOPE.dto.RegisterRequest;
import com.example.SRE.DEVLOPE.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}