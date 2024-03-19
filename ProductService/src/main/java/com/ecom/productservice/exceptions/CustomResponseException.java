package com.ecom.productservice.exceptions;

import com.ecom.productservice.dtos.ErrorDto;
import lombok.Getter;

@Getter
public class CustomResponseException extends RuntimeException {
    private String message;
    private ErrorDto errorDto;

    public CustomResponseException(ErrorDto errorDto) {
        super(errorDto.getMessage());
        this.errorDto = errorDto;
        this.message = errorDto.getMessage();
    }


}