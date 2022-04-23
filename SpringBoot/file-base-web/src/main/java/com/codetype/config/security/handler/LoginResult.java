package com.codetype.config.security.handler;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
class LoginResult {
    //验证码
    private int code;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String token;
    //token过期时间
    private Long expireTime;
    private Long id;
}
