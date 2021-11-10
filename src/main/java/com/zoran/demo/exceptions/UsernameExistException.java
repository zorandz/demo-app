package com.zoran.demo.exceptions;

public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}
