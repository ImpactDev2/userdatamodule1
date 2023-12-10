package com.shopping.userDataModule1.exception;

public class InvalidRequest extends RuntimeException {

    public InvalidRequest() {
        super();
    }

    public InvalidRequest(String message) {
        super(message);
    }

}
