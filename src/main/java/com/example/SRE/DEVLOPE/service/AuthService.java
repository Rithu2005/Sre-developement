package com.example.SRE.DEVLOPE.service;

import com.example.SRE.DEVLOPE.dto.*;
import com.example.SRE.DEVLOPE.model.Role;
import com.example.SRE.DEVLOPE.model.User;
import com.example.SRE.DEVLOPE.repository.UserRepository;
import com.example.SRE.DEVLOPE.util.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(Role.USER);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public AuthResponse login(
            LoginRequest request
    ) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() -> new RuntimeException("User Not Found")
        );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        String token =
                JwtUtil.generateToken(
                        user.getEmail()
                );

        return new AuthResponse(token);
    }
}