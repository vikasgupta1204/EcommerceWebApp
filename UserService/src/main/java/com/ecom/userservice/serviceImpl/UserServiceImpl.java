package com.ecom.userservice.serviceImpl;

import com.ecom.userservice.dtos.ErrorDto;
import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.LoginResponseDto;
import com.ecom.userservice.dtos.SignUpResponseDto;
import com.ecom.userservice.exceptions.CustomResponseException;
import com.ecom.userservice.exceptions.RoleNotFoundException;
import com.ecom.userservice.model.Role;
import com.ecom.userservice.model.Token;
import com.ecom.userservice.model.User;
import com.ecom.userservice.repos.RoleRepo;
import com.ecom.userservice.repos.TokenRepo;
import com.ecom.userservice.repos.UserRepo;
import com.ecom.userservice.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private TokenRepo tokenRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, TokenRepo tokenRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        //1. Check if user exists
        User user = userRepo.findByEmail(loginRequestDto.getEmail());
        if (user == null) {
            //2. If user does not exist, throw exception
            logger.error("User not found with email:" + loginRequestDto.getEmail());
            ErrorDto errorDto =ErrorDto.builder().message("User not found with email:" + loginRequestDto.getEmail()).
                    errorCode("404").errorDetails("User not found in the system").
                    errorType("User Not Found").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .httpStatus(HttpStatus.NOT_FOUND.value()).path("/user/login")
                    .build();
            throw new CustomResponseException(errorDto);
        }
        //3. If user exists, check if password is correct
        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            //4. If password is incorrect, throw exception
            logger.error("Password is incorrect for email:" + loginRequestDto.getEmail());
            ErrorDto errorDto=ErrorDto.builder().message("Password is incorrect for email:" + loginRequestDto.getEmail()).
                    errorCode("401").errorDetails("Invalid username or password").
                    errorType("Authentication Error").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .httpStatus(HttpStatus.UNAUTHORIZED.value()).path("/user/login")
                    .build();
            throw new CustomResponseException(errorDto);
        }
        //5. If password is correct, create token
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128))   ;
        token.setExpiryAt(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        //6. Save token
        Token savedToken = tokenRepo.save(token);
        //7. Return token

        LoginResponseDto responseDto= tokenToLoginResponseDto(savedToken);
        return ResponseEntity.ok(responseDto);
    }

    private LoginResponseDto tokenToLoginResponseDto(Token savedToken) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(savedToken.getValue());
        loginResponseDto.setEmail(savedToken.getUser().getEmail());
        loginResponseDto.setName(savedToken.getUser().getName());
        loginResponseDto.setExpiryAt(savedToken.getExpiryAt());
        List<Role> roles = savedToken.getUser().getRoles();
        StringBuilder role = new StringBuilder();
        roles.stream().forEach(r->role.append(r.getRoleName()).append(","));
        loginResponseDto.setRole(role.toString());
        return loginResponseDto;
    }

    @Override
    public ResponseEntity<String> logout(String token) {
       Optional<Token> tokenOptional = tokenRepo.findByValueAndIsDeletedEquals(token,false);
       if(tokenOptional.isEmpty()) {
           logger.error("Token not found with value:" + token);
           ErrorDto errorDto = ErrorDto.builder().message("Token not found with value:" + token).
                   errorCode("404").errorDetails("Token not found in the system").
                   errorType("Token Not Found").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                   .httpStatus(HttpStatus.NOT_FOUND.value()).path("/user/logout")
                   .build();
           throw new CustomResponseException(errorDto);
       }
       Token tokenToBeSotDeleted = tokenOptional.get();
       tokenToBeSotDeleted.setDeleted(true);
         tokenRepo.save(tokenToBeSotDeleted);
        return ResponseEntity.ok("Logged out successfully");
    }

    @Override
    public ResponseEntity<SignUpResponseDto> signUp(String name, String email, String password, List<String> roles) {
        //1. Check if user already exists
        User checkUser = userRepo.findByEmail(email);
        if (checkUser != null) {
            //2. If user exists, throw exception
            logger.error("User already exists with email:" + email);
            ErrorDto errorDto = ErrorDto.builder().message("User already exists with email:" + email).
                    errorCode("409").errorDetails("User already exists with email").
                    errorType("User Already Exists").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .httpStatus(HttpStatus.CONFLICT.value()).path("/user/signup")
                    .build();
            throw new CustomResponseException(errorDto);
        }

        //3. If user does not exist, create user
        User user = new User();
        user.setEmail(email);
        //4. Encrypt password
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setName(name);
        //5.
        for (int i = 0; i < roles.size(); i++) {
            Role role = roleRepo.findByRoleName(roles.get(i));
            //6. If role does not exist, throw exception
            if (role == null) {
                logger.error("Role not found:" + roles.get(i));
                throw new RoleNotFoundException("Role not found:" + roles.get(i));
            }
            //7. If role exists, add role to user
            if (user.getRoles() == null) {
                List<Role> roleTAdd = new ArrayList<>();
                user.setRoles(roleTAdd);
            }
            user.getRoles().add(role);
            user.setEmailVerified(true);
            logger.info("Role added:" + role.getRoleName());
        }
        //8.
        User savedUser = userRepo.save(user);
        //9. Return user details
        return ResponseEntity.ok(userTosignUpResponseDto(savedUser));
    }

    @Override
    public ResponseEntity<User> validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepo.findByValueAndIsDeletedEquals(token, false);
        if(tokenOptional.isEmpty()) {
            logger.error("Token not found with value:" + token);
            ErrorDto errorDto = ErrorDto.builder().message("Token not found with value:" + token).
                    errorCode("404").errorDetails("Token not found in the system").
                    errorType("Token Not Found").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .httpStatus(HttpStatus.NOT_FOUND.value()).path("/user/validateToken")
                    .build();
            throw new CustomResponseException(errorDto);
        }
        Token tokenToBeValidated = tokenOptional.get();
        return ResponseEntity.ok(tokenToBeValidated.getUser());
    }

    private SignUpResponseDto userTosignUpResponseDto(User savedUser) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setEmail(savedUser.getEmail());
        signUpResponseDto.setName(savedUser.getName());
        return signUpResponseDto;
    }
}
