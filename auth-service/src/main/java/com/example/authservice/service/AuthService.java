package com.example.authservice.service;

import com.example.authservice.domain.User;
import com.example.authservice.repository.UserrRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserrRepository userrRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserrRepository userrRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userrRepository = userrRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userrRepository.save(user);
    }

    public String generateToken(String userName){
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
