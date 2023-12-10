package com.shopping.userDataModule1.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
