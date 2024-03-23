//package com.ecom.userservice.security.models;
//
//import com.ecom.userservice.model.Role;
//import com.ecom.userservice.model.User;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@JsonDeserialize
//@JsonIgnoreProperties(ignoreUnknown = true)
//@NoArgsConstructor
//public class CustomUserDetails implements UserDetails {
//    private static User user;
//    private List<CustomGrantedAuthority> authorities;
//    public CustomUserDetails(User user){
//        this.user=user;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
//        for(Role role:user.getRoles()){
//            CustomGrantedAuthority c= new CustomGrantedAuthority(role);
//            grantedAuthorities.add(c);
//        }
//        return grantedAuthorities;
//    }
//    public void setAuthorities(List<CustomGrantedAuthority> customGrantedAuthorities){
//        this.authorities=customGrantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
