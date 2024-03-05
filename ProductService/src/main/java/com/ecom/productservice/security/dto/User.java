package com.ecom.productservice.security.dto;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String name;
    private String password;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;
}
