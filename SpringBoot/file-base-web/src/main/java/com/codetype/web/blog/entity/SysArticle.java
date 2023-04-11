package com.codetype.web.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 文章标题
 * @TableName sys_article
 */
@Data
@EntityScan
public class SysArticle implements Serializable {
    /**
     * 文章id 
     */
    private Long articleId;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章主体
     */
    private String articleList;

    /**
     * 缩略图
     */
    private String articleImg;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date articleCreate = new Date();
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date articleUpdate = new Date();

    /**
     * 用户表
     */
    private User user;


    private static final long serialVersionUID = 1L;
}