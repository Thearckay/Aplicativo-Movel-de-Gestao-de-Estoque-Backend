package com.thearckay.amge.dto.request;

public record UserLoginRequest(
        String email,
        String password
) {
}
