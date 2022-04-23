package com.codetype.web.blog.Vo;

import lombok.Data;

@Data
public class TokenVo {
    private Long expireTime;
    private String token;
}
