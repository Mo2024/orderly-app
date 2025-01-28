package com.orderly.users.service;

import com.orderly.users.DTO.RegisterRequest;
import com.orderly.users.model.User;
import com.orderly.users.repository.UserRepository;
import com.orderly.users.utility.ApiResponse;
import com.orderly.users.utility.CustomException;
import com.orderly.users.utility.CustomResponseCode;
import com.orderly.users.utility.Functions;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Functions functions;

    @Autowired
    private HttpSession httpSession;

    @Transactional
    public ApiResponse<User> registerUser(RegisterRequest registerRequest) throws CustomException {

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

        if(!Functions.isNotEmpty(registerRequest.getAddress())){
            throw new CustomException(
                    CustomResponseCode.EMPTY_FIELD.getCode(),
                    CustomResponseCode.EMPTY_FIELD.getMessage()
            );
        }

        String hash = Functions.generateHash(registerRequest.getPassword());

        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setHash(hash);
        newUser.setAddress(registerRequest.getAddress());

        User savedUser = userRepository.save(newUser);
        savedUser.setHash(null);

        httpSession.setAttribute("userId", savedUser.getId());

        return new ApiResponse<User>(
                CustomResponseCode.CREATED.getCode(),
                CustomResponseCode.CREATED.getMessage(),
                newUser
        );
    }
}
