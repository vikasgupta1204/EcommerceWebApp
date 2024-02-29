package com.ecom.userservice.exceptions;

import com.ecom.userservice.dtos.ErrorDto;

public class UserNotFoundWithIdException extends RuntimeException {

    public UserNotFoundWithIdException(String message) {
        super(message);
    }


}
