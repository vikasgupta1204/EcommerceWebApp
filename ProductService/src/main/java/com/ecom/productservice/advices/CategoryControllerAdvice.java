package com.ecom.productservice.advices;

import com.ecom.productservice.controller.CategoryController;
import com.ecom.productservice.dtos.ErrorDto;
import com.ecom.productservice.dtos.ExceptionDto;
import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.exceptions.CustomResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = CategoryController.class)
public class CategoryControllerAdvice {
    @ExceptionHandler(CategoryNotFoundException.class)
//@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseBody
    private ResponseEntity<ExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(categoryNotFoundException.getMessage());
        exceptionDto.setStatus("Failure");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<?> handleCustomResponseException(CustomResponseException ex) {
        ErrorDto errorDto = ex.getErrorDto();
        return ResponseEntity.status(errorDto.getHttpStatus()).body(errorDto);
    }
}
