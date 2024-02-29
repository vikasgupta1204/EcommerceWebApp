package com.ecom.userservice.service;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.model.Token;
import com.ecom.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<Token> login(LoginRequestDto loginRequestDto);
    ResponseEntity<User> signUp(SignUpRequestDto signUpRequestDto);
    ResponseEntity<String> logout(String token);

}
