package com.ecom.userservice.service;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.LoginResponseDto;
import com.ecom.userservice.dtos.SignUpResponseDto;
import com.ecom.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto);
    ResponseEntity<String> logout(String token);

    ResponseEntity<SignUpResponseDto> signUp(String name, String email, String password, List<String> roles);
    ResponseEntity<User> validateToken(String token);
}
