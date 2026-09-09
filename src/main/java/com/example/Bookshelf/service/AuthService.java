package com.example.Bookshelf.service;

import com.example.Bookshelf.dtos.authDtos.AuthResponseDto;
import com.example.Bookshelf.dtos.authDtos.LoginRequestDto;
import com.example.Bookshelf.dtos.authDtos.RegisterRequestDto;
import com.example.Bookshelf.models.User;
import com.example.Bookshelf.repostiories.UserRepository;
import com.example.Bookshelf.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    public AuthService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto register(RegisterRequestDto dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("This email is aready beign used.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                encodedPassword
        );
        repository.save(user);
        String token = jwtUtil.generateToken(dto.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Email or Password is invalid.");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
