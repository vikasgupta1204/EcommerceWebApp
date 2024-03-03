package com.ecom.userservice.controller;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.LoginResponseDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.dtos.SignUpResponseDto;
import com.ecom.userservice.model.Token;
import com.ecom.userservice.model.User;
import com.ecom.userservice.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getRoles());
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token) {
        return userService.logout(token);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<User> validateToken(@RequestParam("token") String token) {
        return userService.validateToken(token);
    }
}
