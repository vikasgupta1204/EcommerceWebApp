package com.ecom.productservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ErrorDto {
    private String message;
    private String errorCode;
    private String errorType;
    private String errorDetails;
    private Integer httpStatus;
    private String path;
    private Timestamp timestamp;
}
