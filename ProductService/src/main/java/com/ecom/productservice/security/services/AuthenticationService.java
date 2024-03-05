package com.ecom.productservice.security.services;

import com.ecom.productservice.security.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public boolean authenticate(String token){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<User> userResponseEntity= restTemplate.postForEntity("http://localhost:8080/user/validateToken?token="+token,null,
                User.class);
        if(userResponseEntity.getBody() !=null)return true;
        return false;
    }

    /*
    * Client->user service(Auth Server)
    * Token->Client
    * Client->Product service with Token--->Userservice /validate
    * */
}
