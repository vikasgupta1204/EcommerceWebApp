package com.ecom.productservice.security.services;
import com.ecom.productservice.dtos.ErrorDto;
import com.ecom.productservice.dtos.ErrorResponse;
import com.ecom.productservice.exceptions.CustomResponseException;
import com.ecom.productservice.security.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuthenticationService {
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public boolean authenticate(String token) throws JsonProcessingException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        try {
            ResponseEntity<User> userResponseEntity = restTemplate.exchange("http://localhost:8080/user/validateToken?token=" + token,
                    HttpMethod.POST,
                    null,
                    User.class);
            if(userResponseEntity.getBody() !=null)return true;
        }
        catch (HttpClientErrorException e){
            String responseBodyAsString=e.getResponseBodyAsString();
            HttpStatus httpStatus= (HttpStatus) e.getStatusCode();
            HttpHeaders headers=e.getResponseHeaders();
            System.out.println("Response body:"+responseBodyAsString);
            System.out.println("Http Status:"+httpStatus);
            System.out.println("Headers:"+headers);
            ObjectMapper objectMapper=new ObjectMapper();
            ErrorResponse response= objectMapper.readValue(responseBodyAsString, ErrorResponse.class);
            ErrorDto errorDto=ErrorDto.builder().message(response.getMessage()).
                    timestamp(response.getTimestamp()).errorCode(response.getErrorCode())
                    .errorDetails(response.getErrorDetails())
                    .errorType(response.getErrorType())
                    .httpStatus(response.getHttpStatus())
                    .errorCode(response.getErrorCode())
                    .path(response.getPath())
                    .build();
            throw new CustomResponseException(errorDto);
        }

        return false;
    }

    /*
    * Client->user service(Auth Server)
    * Token->Client
    * Client->Product service with Token--->Userservice /validate
    * */
}
