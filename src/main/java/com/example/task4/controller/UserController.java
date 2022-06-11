package com.example.task4.controller;

import com.example.task4.payload.request.EditUsersRequest;
import com.example.task4.payload.request.RegisterDto;
import com.example.task4.payload.request.UserLoginDto;
import com.example.task4.payload.response.ApiResponse;
import com.example.task4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = userService.register(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        ApiResponse apiResponse = userService.login(userLoginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable @Valid @NotNull Long id) {
        ApiResponse apiResponse = userService.deleteById(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping()
    public HttpEntity<?> edit(@RequestBody @Valid EditUsersRequest editUsersRequest) {
        return ResponseEntity.ok("working");
    }
}
