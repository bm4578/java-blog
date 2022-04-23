package com.codetype.web.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_menu
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 菜单主键
     */
    private Integer menuId;

    /**
     * 权限信息：0/管理员，1/普通用户
     */
    private Integer menuPermissio;

    /**
     * 菜单名称
     */
    private String menuList;

    /**
     * 菜单路径
     */
    private String menuSrc;
    /**
     * 菜单类型
     */
    private Integer menuType;

    private static final long serialVersionUID = 1L;
}