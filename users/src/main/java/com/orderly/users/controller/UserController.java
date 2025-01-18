package com.orderly.users.controller;

import com.orderly.users.DTO.RegisterRequest;
import com.orderly.users.model.User;
import com.orderly.users.service.UserService;
import com.orderly.users.utility.ApiResponse;
import com.orderly.users.utility.CustomException;
import com.orderly.users.utility.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest registerRequest) throws CustomException {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok(new ApiResponse<String>(CustomResponseCode.CREATED.getCode(), CustomResponseCode.CREATED.getMessage(), null)

        );
    }
}
