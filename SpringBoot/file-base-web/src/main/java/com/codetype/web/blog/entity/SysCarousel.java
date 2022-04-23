package com.codetype.web.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName sys_carousel
 */
@Data
public class SysCarousel implements Serializable {
    /**
     * 主键
     */
    private Integer sysId;

    /**
     * 轮播图
     */
    private String sysUrl;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date sysUpdate = new Date();
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date sysCreate = new Date();

    private static final long serialVersionUID = 1L;
}