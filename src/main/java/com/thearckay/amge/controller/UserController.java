package com.thearckay.amge.controller;

import com.thearckay.amge.dto.request.UserLoginRequest;
import com.thearckay.amge.dto.request.UserRegisterRequest;
import com.thearckay.amge.dto.response.ApiResponse;
import com.thearckay.amge.dto.response.LoginResponse;
import com.thearckay.amge.dto.response.UserResponse;
import com.thearckay.amge.entity.Item;
import com.thearckay.amge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public UserResponse getUserTest(){
        return new UserResponse("Kayck Arcanjo", List.of(new Item()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest registerInformations){
        System.out.println("email: "+registerInformations.email());
        System.out.println("password: "+registerInformations.password());

        ApiResponse apiResponse = userService.registerUser(registerInformations);
        return ResponseEntity.ok(apiResponse) ;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest loginInformations){
        System.out.println("email: "+loginInformations.email());
        System.out.println("password: "+loginInformations.password());
        ApiResponse<?> loginResponse =  userService.loginUser(loginInformations);
        return ResponseEntity.ok(loginResponse);
    }
}
