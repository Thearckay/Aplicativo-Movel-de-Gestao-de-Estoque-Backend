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

    public ApiResponse<Integer> registerUser(UserRegisterRequest registerInformations) {
        try {
            User userFromDataBase = userRepository.findUserByEmail(registerInformations.email());
            if (userFromDataBase != null) {
                throw new UserRegisterException("Usuário já existente", HttpStatus.CONFLICT.value());
            }

            User newUser = registerInformations.build();
            if (newUser == null) {
                throw new UserRegisterException("Dados inválidos", HttpStatus.BAD_REQUEST.value());
            }

            User savedUser = userRepository.save(newUser);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registrado com sucesso!",
                    savedUser.getId()
            );

        } catch (UserRegisterException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao registrar usuário: " + e.getMessage());
        }
    }

    //todo - ao errar a senha dá erro interno do servidor
    public ApiResponse<Integer> loginUser(UserLoginRequest loginInformations) {
        try {
            String loginEmail = loginInformations.email();
            User userFromDataBase = userRepository.findUserByEmail(loginEmail);

            if (userFromDataBase == null) {
                throw new UserLoginException("Usuário não cadastrado.", HttpStatus.NOT_FOUND.value());
            }

            if (!loginInformations.password().equals(userFromDataBase.getPassword())) {
                throw new UserLoginException("E-mail ou senha incorretos.", HttpStatus.UNAUTHORIZED.value());
            }

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Login realizado com sucesso!",
                    userFromDataBase.getId()
            );

        } catch (UserLoginException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no servidor: " + e.getMessage());
        }
    }
}
