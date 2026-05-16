package com.example.Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.User;
import com.example.Backend.Repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // ✅ LOGIN LOGIC (FULL SECURITY)
    public User login(String username, String password) {

        User user = repo.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🔥 IMPORTANT: CHECK STATUS
        if (!"APPROVED".equals(user.getStatus())) {
            throw new RuntimeException("Your account is not approved by admin yet");
        }

        return user;
    }

    // ✅ REGISTER LOGIC
    public User register(User user) {

        // 🔥 check duplicate
        if (repo.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        // 🔥 DEFAULT VALUES
        user.setRole("USER");
        user.setStatus("PENDING");

        return repo.save(user);
    }
}