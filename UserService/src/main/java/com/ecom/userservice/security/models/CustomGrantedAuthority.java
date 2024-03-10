package com.ecom.userservice.security.models;

import com.ecom.userservice.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomGrantedAuthority implements GrantedAuthority {

    @JsonProperty("roleName")
    private Role role;

    public CustomGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
