package com.peerlender.lendingengine.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super("User with Id " + userId + " not found");
    }
}
