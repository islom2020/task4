package com.example.task4.payload.response;

import com.example.task4.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse extends ApiResponse {

    private List<User> userList;

}
