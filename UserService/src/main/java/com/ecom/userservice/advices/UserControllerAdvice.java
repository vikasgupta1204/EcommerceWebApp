package com.ecom.userservice.advices;


import com.ecom.userservice.dtos.ErrorDto;
import com.ecom.userservice.exceptions.CustomResponseException;
import com.ecom.userservice.exceptions.RoleNotFoundException;
import com.ecom.userservice.exceptions.UserNotFoundWithIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundWithIdException.class)
    public ResponseEntity<?> userNotFoundWithIdException(UserNotFoundWithIdException ex) {
       ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).
               errorCode("404").errorDetails("User not found in the system").
                errorType("User Not Found").timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .httpStatus(404).path("/user").build();
       return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> roleNotFoundException(RoleNotFoundException ex) {
       ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).
               errorCode("404").errorDetails("Role not found in the system").
               errorType("Role Not Found").timestamp(Timestamp.valueOf(LocalDateTime.now()))
               .httpStatus(404).path("/user/signup").build();
    return ResponseEntity.status(404).body(errorDto);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).
                errorCode("500").errorDetails("Internal Server Error").
                errorType("Internal Server Error").
                timestamp(Timestamp.valueOf(LocalDateTime.now())).httpStatus(500).path("/user").build();
        return ResponseEntity.status(500).body(errorDto);

    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<?> handleCustomResponseException(CustomResponseException ex) {
        ErrorDto errorDto = ex.getErrorDto();
        return ResponseEntity.status(errorDto.getHttpStatus()).body(errorDto);
    }
}
