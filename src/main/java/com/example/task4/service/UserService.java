package com.example.task4.service;

import com.example.task4.entity.User;
import com.example.task4.exception.customException.IdNotFoundException;
import com.example.task4.exception.customException.InvalidPasswordException;
import com.example.task4.payload.request.EditUsersRequest;
import com.example.task4.payload.request.RegisterDto;
import com.example.task4.payload.request.UserLoginDto;
import com.example.task4.payload.response.ApiResponse;
import com.example.task4.payload.response.JwtResponse;
import com.example.task4.repository.UserRepository;
import com.example.task4.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;

    public ApiResponse register(RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        String email = registerDto.getEmail();
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) return new ApiResponse(false, "This email " + email + " is already registered");

        String generatedToken = jwtProvider.generateToken(email);

        User user = new User();
        modelMapper.map(registerDto, user);
        User savedUser = userRepository.save(user);

        return new JwtResponse(true, "User successfully registered and login", savedUser, generatedToken);
    }

    public ApiResponse login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) return new ApiResponse(false, "This email is not registered!");
        User user = optionalUser.get();
        String password = userLoginDto.getPassword();
        if (passwordEncoder.matches(password, user.getPassword())) {
            String generatedToken = jwtProvider.generateToken(email);
            return new JwtResponse(true, "User successfully login", user, generatedToken);
        } else {
            throw new InvalidPasswordException("Incorrect password");
        }
    }

    public ApiResponse deleteById(Long id){
        boolean existsById = userRepository.existsById(id);
        if (!existsById) throw new IdNotFoundException("Id not found");
        userRepository.deleteById(id);
        return new ApiResponse(true,"User deleted successfully");
    }

    public ApiResponse edit(EditUsersRequest editUsersRequest){
        @NotEmpty(message = "Id list cannot be empty.") Long[] ids = editUsersRequest.getIds();
        return null;
    }
}
