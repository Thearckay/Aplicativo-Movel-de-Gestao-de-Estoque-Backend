package com.thearckay.amge.exceptions;

public class UpdateItemException extends RuntimeException {
    private final Integer statusHttp;
    public UpdateItemException(String message, Integer statusHttp) {
        super(message);
        this.statusHttp = statusHttp;
    }

    public Integer getStatusHttp() {
        return statusHttp;
    }
}
