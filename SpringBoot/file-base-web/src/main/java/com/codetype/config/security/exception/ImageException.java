package com.codetype.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class ImageException extends AuthenticationException {
    public ImageException(String msg) {
        super(msg);
    }
}
