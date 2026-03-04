package com.thearckay.amge.exceptions;

public class DeleteItemException extends RuntimeException {
    private final int status;

    public DeleteItemException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
