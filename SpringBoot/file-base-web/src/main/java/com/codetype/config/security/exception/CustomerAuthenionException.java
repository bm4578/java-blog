package com.codetype.config.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * token异常
 */
public class CustomerAuthenionException extends AuthenticationException {
    public CustomerAuthenionException(String msg) {
        super(msg);
    }
}
