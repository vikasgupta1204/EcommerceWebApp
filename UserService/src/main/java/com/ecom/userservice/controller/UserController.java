package com.ecom.userservice.controller;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.model.User;
import com.ecom.userservice.service.UserService;
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
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token) {
        return userService.logout(token);
    }



}
