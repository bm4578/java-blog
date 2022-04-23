package com.codetype.web.blog.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户表
 * @TableName sys_user
 */
@Data
@EntityScan
public class User implements Serializable , UserDetails {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 文章id
     */
    private String isArticle;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String isImg;
    /**
     * 验证码
     */
    private String code;
    /**
     * 权限字段0/管理员，1/普通用户
     */
    private Boolean isPermission = true;

    /**
     * 帐户是否过期(1 未过期，0已过期)
     */
    private Boolean isAccountNonExpired = true;
    /**
     *帐户是否被锁定(1 未锁定，0已锁定)
     */
    private Boolean isAccountNonLocked = true;
    /**
     *
     */
    //密码是否过期(1 未过期，0已过期)
    private Boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用(1 可用，0 删除用户)
     */
    private Boolean isEnabled = true;

    /**
     * 存储用户权限字段
     */
    Collection<? extends GrantedAuthority> authorities;

    /**
     * 创建时间
     *   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createTime = new Date();
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime = new Date();

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}