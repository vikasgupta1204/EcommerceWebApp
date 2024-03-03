package com.ecom.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private String email;
    private String name;
    private String role;
    private Date expiryAt;
}
