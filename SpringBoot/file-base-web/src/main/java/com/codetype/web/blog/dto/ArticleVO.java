package com.codetype.web.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户文章列表
 */
@Data
public class ArticleVO {

    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date articleCreate = new Date();
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date articleUpdate = new Date();
}
