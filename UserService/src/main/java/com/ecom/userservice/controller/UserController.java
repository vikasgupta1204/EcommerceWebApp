package com.ecom.userservice.controller;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.LoginResponseDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.dtos.SignUpResponseDto;
import com.ecom.userservice.model.User;
import com.ecom.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Sign up a new user")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "User signed up successfully"),
           @ApiResponse(responseCode = "400", description = "Invalid input"),
           @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getRoles());
    }

    @Operation(summary = "Login a user")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "User logged in successfully"),
              @ApiResponse(responseCode = "400", description = "Invalid input"),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
     })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @Operation(summary = "Logout a user")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "User logged out successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "401", description = "Unauthorized")
         })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token) {
        return userService.logout(token);
    }

    @Operation(summary = "Validate a token")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Token is valid"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "401", description = "Unauthorized")
         })
    @PostMapping("/validateToken")
    public ResponseEntity<User> validateToken(@RequestParam("token") String token) {
        return userService.validateToken(token);
    }

}
