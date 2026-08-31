package com.example.Bookshelf.dtos.authDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @Email(message = "Email mut have @ and .com")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
