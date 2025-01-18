package com.orderly.users.utility;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CustomResponseCode {
    SUCCESS("USR-2000", "Success"),
    CREATED("USR-2010", "Created successfully"),
    UPDATED("USR-2020", "Updated successfully"),
    DELETED("USR-2030", "Deleted successfully"),
    USER_NOT_FOUND("USR-4001", "User not found"),
    INVALID_REQUEST("USR-4002", "Invalid request"),
    UNAUTHORIZED("USR-4003", "Unauthorized access"),
    INTERNAL_SERVER_ERROR("USR-5001", "Internal server error"),

    INVALID_EMAIL("6001", "Kindly enter a valid email"),
    PASSWORD_NOT_STRONG("USR-6002", "Kindly ensure password is at least 8 digits long."),
    PASSWORD_MISMATCH("USR-6003", "Kindly ensure that both password are matching"),
    DUPLICATE_EMAIL("USR-6004", "Email already exists!");

    private final String code;
    private final String message;

}
