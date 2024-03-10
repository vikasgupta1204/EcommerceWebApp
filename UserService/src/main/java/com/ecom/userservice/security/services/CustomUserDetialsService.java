package com.ecom.userservice.security.services;

import com.ecom.userservice.dtos.ErrorDto;
import com.ecom.userservice.exceptions.CustomResponseException;
import com.ecom.userservice.model.User;
import com.ecom.userservice.repos.UserRepo;
import com.ecom.userservice.security.models.CustomGrantedAuthority;
import com.ecom.userservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetialsService implements UserDetailsService {

    private UserRepo userRepo;
    @Autowired
    public CustomUserDetialsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=
                Optional.ofNullable(userRepo.findByEmail(username));

        User user=userOptional.get();

        if(user==null) {
            ErrorDto errorDto = ErrorDto.builder()
                    .errorType("NO_USER_FOUND")
                    .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .errorCode("404")
                    .path("/login")
                    .message("User not found")
                    .errorDetails("User not found with username:" + username)
                    .httpStatus(404)
                    .build();
            throw new CustomResponseException(errorDto);
        }

        CustomUserDetails customUserDetails=
                new CustomUserDetails(user);
        customUserDetails.setAuthorities(user.getRoles().stream().map(
                CustomGrantedAuthority::new
        ).collect(Collectors.toList()));
        return customUserDetails;
    }
}
