package com.ecom.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private List<String> roles;
}
