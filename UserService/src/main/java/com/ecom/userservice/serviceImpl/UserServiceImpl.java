package com.ecom.userservice.serviceImpl;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.SignUpRequestDto;
import com.ecom.userservice.exceptions.RoleNotFoundException;
import com.ecom.userservice.exceptions.WrongCredentialsExceptions;
import com.ecom.userservice.model.Role;
import com.ecom.userservice.model.Token;
import com.ecom.userservice.model.User;
import com.ecom.userservice.repos.RoleRepo;
import com.ecom.userservice.repos.TokenRepo;
import com.ecom.userservice.repos.UserRepo;
import com.ecom.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    UserRepo userRepo;
    RoleRepo roleRepo;
    TokenRepo tokenRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public ResponseEntity<Token> login(LoginRequestDto loginRequestDto) {
        User user = userRepo.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if (user == null) {
            logger.error("Email or password is incorrect:{}||{}", loginRequestDto.getEmail(), loginRequestDto.getPassword());
            throw new WrongCredentialsExceptions("Email or password is incorrect");
        }

        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long thirtyMinutes = 30 * 60 * 1000;
        timestamp.setTime(timestamp.getTime() + thirtyMinutes);
        token.setExpiryDate(timestamp);
       Token savedToken=  tokenRepo.save(token);
        logger.info("User logged in successfully:" + user.getEmail());
        return ResponseEntity.ok(savedToken);
    }

    @Override
    public ResponseEntity<User> signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());
        user.setName(signUpRequestDto.getName());
        for (int i = 0; i < signUpRequestDto.getRoles().size(); i++) {
            Role role = roleRepo.findByRoleName(signUpRequestDto.getRoles().get(i));
            if (role == null) {
                logger.error("Role not found:" + signUpRequestDto.getRoles().get(i));
                throw new RoleNotFoundException("Role not found:" + signUpRequestDto.getRoles().get(i));
            }
            if (user.getRoles() == null) {
                List<Role> roles = new ArrayList<>();
                user.setRoles(roles);
            }
            user.getRoles().add(role);
            logger.info("Role added:" + role.getRoleName());
        }
        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @Override
    public ResponseEntity<String> logout(String token) {
        Token token1 = tokenRepo.findByToken(token);
        if (token1 == null) {
            logger.error("Token not found:" + token);
            throw new RuntimeException("Token not found");
        }
        tokenRepo.delete(token1);
        return ResponseEntity.ok("Logged out successfully");
    }
}
