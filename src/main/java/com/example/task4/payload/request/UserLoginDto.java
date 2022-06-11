package com.example.task4.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDto {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

}
