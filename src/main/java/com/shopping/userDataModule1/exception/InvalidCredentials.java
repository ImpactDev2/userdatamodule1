package com.shopping.userDataModule1.exception;

public class InvalidCredentials extends RuntimeException {

    public InvalidCredentials() {
        super();
    }

    public InvalidCredentials(String message) {
        super(message);
    }
}
