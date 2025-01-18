package com.orderly.users.utility;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Functions {

    private static final String EMAIL_REGEX = "^[^@.]+@[^@]+\\.[^@]+$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }
}
