package com.example.merchant.service;

import com.example.merchant.config.JwtTokenProvider;
import com.example.merchant.entity.Authentication;
import com.example.merchant.repository.AuthenticationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationRequestRepository authenticationRequestRepository;

    private final JwtTokenProvider jwtUtil;

    public String authenticateUser(String email, String password) {
        Optional<Authentication> userOptional = authenticationRequestRepository.findByEmail(email);

        if (userOptional.isPresent() && password.equals(userOptional.get().getPassword())) {
            String token = jwtUtil.generateToken(email);
            Authentication user = userOptional.get();
            user.setToken(token);
            authenticationRequestRepository.save(user);
            return token;
        }

        throw new RuntimeException("Invalid credentials");
    }
}