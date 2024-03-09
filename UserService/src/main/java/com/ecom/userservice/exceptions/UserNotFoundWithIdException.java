package com.ecom.userservice.exceptions;

public class UserNotFoundWithIdException extends RuntimeException {

    public UserNotFoundWithIdException(String message) {
        super(message);
    }


}
