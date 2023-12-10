package com.shopping.userDataModule1.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException() {
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
