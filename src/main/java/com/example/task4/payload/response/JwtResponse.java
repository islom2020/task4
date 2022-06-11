package com.example.task4.payload.response;

import com.example.task4.entity.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class JwtResponse extends ApiResponse {

    private User user;
    private String token;

    public JwtResponse(boolean success, String message, User user, String token) {
        super(success, message);
        this.user = user;
        this.token = token;
    }
}
