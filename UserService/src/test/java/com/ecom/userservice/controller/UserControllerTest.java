package com.ecom.userservice.controller;

import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.model.Role;
import com.ecom.userservice.model.User;
import com.ecom.userservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private UserController userController;


    @Test
    void signUp() {
        SignUpRequestDto mockUser = new SignUpRequestDto();
        mockUser.setName("test");
        mockUser.setEmail("test email");
        mockUser.setPassword("test password");
        List<String> roles = List.of("ROLE_USER");
        mockUser.setRoles(roles);
        User user=new User();
        user.setName(mockUser.getName());
        user.setEmail(mockUser.getEmail());
        user.setPassword(mockUser.getPassword());
        Role role=new Role();
        role.setRoleName(mockUser.getRoles().get(0));
        List rolesList=List.of(role);
        user.setRoles(rolesList);
        when(userService.signUp(mockUser)).thenReturn(ResponseEntity.ok(user));
        User saveedUser=userController.signUp(mockUser).getBody();
        assertEquals(user,saveedUser);
        assertEquals(user.getName(),saveedUser.getName());
        assertEquals(user.getEmail(),saveedUser.getEmail());
        assertEquals(user.getPassword(),saveedUser.getPassword());
        assertEquals(user.getRoles(),saveedUser.getRoles());
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }
}