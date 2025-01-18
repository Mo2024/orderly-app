package com.orderly.users.service;

import com.orderly.users.DTO.RegisterRequest;
import com.orderly.users.model.User;
import com.orderly.users.repository.UserRepository;
import com.orderly.users.utility.CustomException;
import com.orderly.users.utility.CustomResponseCode;
import com.orderly.users.utility.Functions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orderly.users.utility.GlobalExceptionHandler.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Functions functions;

    @Transactional
    public void registerUser(RegisterRequest registerRequest) throws CustomException {

        if (!Functions.isValidEmail(registerRequest.getEmail())){
            throw new CustomException(
                    CustomResponseCode.INVALID_EMAIL.getCode(),
                    CustomResponseCode.INVALID_EMAIL.getMessage()
            );
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new CustomException(
                    CustomResponseCode.DUPLICATE_EMAIL.getCode(),
                    CustomResponseCode.DUPLICATE_EMAIL.getMessage()
            );
        }

        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            throw new CustomException(
                    CustomResponseCode.PASSWORD_MISMATCH.getCode(),
                    CustomResponseCode.PASSWORD_MISMATCH.getMessage()
            );
        }

    }
}
