package com.stuflo.roamiospring.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email field cannot be empty")
    private String email;

    @NotEmpty(message = "Password field cannot be empty")
    private String password;
}
