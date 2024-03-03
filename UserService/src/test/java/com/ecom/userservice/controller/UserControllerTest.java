package com.ecom.userservice.controller;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.dtos.SignUpResponseDto;
import com.ecom.userservice.model.Role;
import com.ecom.userservice.model.Token;
import com.ecom.userservice.model.User;
import com.ecom.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private UserController userController;


    @Test
    void signUp() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("test");
        signUpRequestDto.setEmail("test@email.com");
        signUpRequestDto.setPassword("test");
        signUpRequestDto.setRoles(List.of("ADMIN"));
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setName("test");
        signUpResponseDto.setEmail("test@gmail.com");
        signUpResponseDto.setEmailVerified(true);
        when(userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getRoles())).
                thenReturn(ResponseEntity.ok(signUpResponseDto));
        ResponseEntity<SignUpResponseDto> responseEntity = userController.signUp(signUpRequestDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals("test", responseEntity.getBody().getName());
        assertEquals("test@gmail.com", responseEntity.getBody().getEmail());
    }

    @Test
    void login() {
        String email = "test@email.com";
        String password = "test";
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);
        Token mockToken = new Token();
        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(password);
        mockUser.setName("test");
        Role mockRole = new Role();
        mockRole.setRoleName("ADMIN");
        mockUser.setRoles(List.of(mockRole));
        mockToken.setUser(mockUser);
        mockToken.setValue(UUID.randomUUID().toString());
        mockToken.setExpiryAt(Timestamp.valueOf("2022-12-12 00:00:00.0"));
      //  when(userService.login(loginRequestDto)).thenReturn(ResponseEntity.ok(mockToken));
      //  ResponseEntity<Token> responseEntity = userController.login(loginRequestDto);
      //  assertEquals(200, responseEntity.getStatusCodeValue());
     //   Token token = responseEntity.getBody();
//        assertNotNull(token);
//        assertEquals(mockToken.getValue(), token.getValue());
//        assertEquals(mockToken.getExpiryAt(), token.getExpiryAt());
//        assertEquals(mockToken.getUser().getEmail(), token.getUser().getEmail());
//        assertEquals(mockToken.getUser().getName(), token.getUser().getName());
//        assertEquals(mockToken.getUser().getRoles().get(0).getRoleName(), token.getUser().getRoles().get(0).getRoleName());

    }

    @Test
    public void logout() {
        String token = UUID.randomUUID().toString();
        when(userService.logout(token)).thenReturn(ResponseEntity.ok("User logged out successfully"));
        ResponseEntity<String> responseEntity = userController.logout(token);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("User logged out successfully", responseEntity.getBody());
    }

    @Test
    public void validateToken() {
        String token = UUID.randomUUID().toString();
        User mockUser = new User();
        mockUser.setEmail("test@email.com");
        mockUser.setPassword("test");
        mockUser.setName("test");
        when(userService.validateToken(token)).thenReturn(ResponseEntity.ok(mockUser));
        ResponseEntity<User> responseEntity = userController.validateToken(token);
        assertEquals(200, responseEntity.getStatusCodeValue());
        User user = responseEntity.getBody();
        assertNotNull(user);
        assertEquals(mockUser.getEmail(), user.getEmail());
        assertEquals(mockUser.getName(), user.getName());
        assertEquals(mockUser.getPassword(), user.getPassword());

    }

}