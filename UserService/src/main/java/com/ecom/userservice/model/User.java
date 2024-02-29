package com.ecom.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String password;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

}
