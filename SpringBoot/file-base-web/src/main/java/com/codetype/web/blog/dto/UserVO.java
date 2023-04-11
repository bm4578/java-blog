package com.codetype.web.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class UserVO{
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 文章id
     */
    private Integer isArticle;
    /**
     * 用户名
     */
    private String username;
    /**
     *权限名称
     */
    private Integer isPermission;

    /**
     * 是否禁用账户
     */
    private Integer isEnabled;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createTime = new Date();


}
