package com.udh.auth.domain.exception;

import lombok.AllArgsConstructor;

public class AuthenticationException extends AppException {
    public AuthenticationException(String message) {
        super(message);
    }
}
