package com.example.Bookshelf.dtos.authDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "Email must have @ and .com")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
