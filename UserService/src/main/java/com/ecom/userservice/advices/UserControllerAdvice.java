package com.ecom.userservice.advices;


import com.ecom.userservice.dtos.ErrorDto;
import com.ecom.userservice.exceptions.RoleNotFoundException;
import com.ecom.userservice.exceptions.UserNotFoundWithIdException;
import com.ecom.userservice.exceptions.WrongCredentialsExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundWithIdException.class)
    public ResponseEntity<?> userNotFoundWithIdException(UserNotFoundWithIdException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setHttpStatus(404);
        errorDto.setErrorCode("404");
        errorDto.setErrorDetails("User not found in the system");
        errorDto.setErrorType("User Not Found");
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> roleNotFoundException(RoleNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setErrorType("Role Not Found");
        errorDto.setErrorDetails("Role not found in the system");
        errorDto.setErrorCode("404");
        errorDto.setHttpStatus(404);
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return ResponseEntity.status(404).body(errorDto);

    }

    @ExceptionHandler(WrongCredentialsExceptions.class)
    public ResponseEntity<?> handleIncorrectCredentialsException(WrongCredentialsExceptions ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("401");
        errorDto.setErrorDetails("Invalid username or password");
        errorDto.setErrorType("Authentication Error");
        errorDto.setMessage(ex.getMessage());
        errorDto.setHttpStatus(401);
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return ResponseEntity.status(401).body(errorDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("500");
        errorDto.setErrorDetails("Internal Server Error");
        errorDto.setErrorType("Internal Server Error");
        errorDto.setMessage(ex.getMessage());
        errorDto.setHttpStatus(500);
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return ResponseEntity.status(500).body(errorDto);

    }
}
