package com.ecom.userservice.exceptions;

public class WrongCredentialsExceptions extends RuntimeException {
    public WrongCredentialsExceptions(String message) {
        super(message);
    }
}
