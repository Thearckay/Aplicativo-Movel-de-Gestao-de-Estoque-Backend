package com.thearckay.amge.service;

import com.thearckay.amge.dto.request.UserLoginRequest;
import com.thearckay.amge.dto.request.UserRegisterRequest;
import com.thearckay.amge.dto.response.ApiResponse;
import com.thearckay.amge.dto.response.LoginResponse;
import com.thearckay.amge.entity.Item;
import com.thearckay.amge.entity.User;
import com.thearckay.amge.exceptions.UserLoginException;
import com.thearckay.amge.exceptions.UserRegisterException;
import com.thearckay.amge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiResponse<?> registerUser(UserRegisterRequest registerInformations){
        try{
            // verificar de já não há esse cadastro
            User userFromDataBase = userRepository.findUserByEmail(registerInformations.email());
            if (userFromDataBase != null) throw new UserRegisterException("Usuário já existente", HttpStatus.CONFLICT.value());

            // transforma o DTO em um User para inserir no banco de dados
            User newUser = registerInformations.build();
            if (newUser == null){
                throw new UserRegisterException("Dados inválidos", HttpStatus.BAD_REQUEST.value());
            }

            userRepository.save(newUser);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registrado com sucesso!",
                    null
            );

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse<?> loginUser(UserLoginRequest loginInformations){
        try {
            String loginEmail = loginInformations.email();
            User userFromDataBase = userRepository.findUserByEmail(loginEmail);

            if (userFromDataBase == null){
                throw new UserLoginException("Usuário não existe.", HttpStatus.BAD_REQUEST.value());
            }

            if (!loginInformations.password().equals(userFromDataBase.getPassword())){
                throw new UserLoginException("Senha inválida.", HttpStatus.BAD_REQUEST.value());
            }

            String name = userFromDataBase.getName();
            List<Item> itemList = userFromDataBase.getItemList();

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Logado com sucesso!",
                    itemList
            );

        } catch (UserLoginException e) {
            throw new UserLoginException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
