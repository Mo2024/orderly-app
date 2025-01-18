package com.orderly.users.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String responseCode;
    private String message;
    private T data;
}
