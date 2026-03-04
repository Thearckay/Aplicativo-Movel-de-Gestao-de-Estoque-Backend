package com.thearckay.amge.exceptions;

public class RegisterItemException extends RuntimeException {
    private final int status;
    public RegisterItemException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
