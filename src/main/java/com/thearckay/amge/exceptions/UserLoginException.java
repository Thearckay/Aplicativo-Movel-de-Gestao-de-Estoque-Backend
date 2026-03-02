package com.thearckay.amge.exceptions;

public class UserLoginException extends RuntimeException {
    private Integer status;

    public UserLoginException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
