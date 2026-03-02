package com.thearckay.amge.dto.request;

import com.thearckay.amge.entity.User;

public record UserRegisterRequest(
        String name,
        String email,
        String password
) {

    public User build(){
        return new User(
                name,
                email,
                password
        );
    }
}
