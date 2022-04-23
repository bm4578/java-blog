package com.codetype.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserException extends AuthenticationException {
    public UserException(String msg) {
        super(msg);
    }
}
