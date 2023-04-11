package com.codetype.web.blog.dto;

import lombok.Data;

/**
 * @author 白也
 * @date 2023/4/11 8:27 下午
 * @title
 */
@Data
public class SearchVO {
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章id
     */
    private Long articleId;
}
