package com.ecom.userservice.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
public class SignUpResponseDto {
    private String name;
    private String email;
    private boolean isEmailVerified;
}
