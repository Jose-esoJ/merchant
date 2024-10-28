package com.example.merchant.controller;

import com.example.merchant.entity.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    //private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequest request) {
        String token = "";//authenticationService.authenticate(request.getEmail(), request.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}