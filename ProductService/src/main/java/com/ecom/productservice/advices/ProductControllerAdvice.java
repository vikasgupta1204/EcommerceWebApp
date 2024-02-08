package com.ecom.productservice.advices;

import com.ecom.productservice.controller.ProductController;
import com.ecom.productservice.dtos.ExceptionDto;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
//@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
//@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseBody
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(productNotFoundException.getMessage());
        exceptionDto.setStatus("Failure");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
