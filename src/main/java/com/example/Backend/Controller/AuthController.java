package com.example.Backend.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Backend.Entity.User;
import com.example.Backend.Service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://govindanvegetables.netlify.app")
public class AuthController {

    @Autowired
    private AuthService service;

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req) {
        try {
            User user = service.login(req.getUsername(), req.getPassword());

            return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "role", user.getRole(),
                "status", user.getStatus()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = service.register(user);

            return ResponseEntity.ok(Map.of(
                "message", "Registered successfully. Waiting for admin approval",
                "username", savedUser.getUsername()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}