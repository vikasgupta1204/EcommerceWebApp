package com.ecom.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ErrorDto {
    private String message;
    private String errorCode;
    private String errorType;
    private String errorDetails;
    private Integer HttpStatus;
    private String path;
    private Timestamp timestamp;

}
