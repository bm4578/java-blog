package com.codetype.web.blog.mapper;

import com.codetype.web.blog.dto.ArticleVO;
import com.codetype.web.blog.entity.SysArticle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysArticleMapperTest {
    @Autowired
    private SysArticleMapper sysArticleMapper;

    @Test
    void selectArticle() {
    }

    @Test
    void selectArticleId() {
    }

    @Test
    void selectFrequency() {
        Integer integer = sysArticleMapper.selectFrequency(1);
        System.out.println(integer);
    }

    @Test
    void addArticle() {
    }

    @Test
    void articleList() {
        List<ArticleVO> articleVOS = sysArticleMapper.articleList();
        System.out.println(articleVOS);
    }

    @Test
    void updateArticle() {
        SysArticle sysArticle = new SysArticle();
        sysArticle.setArticleId(3);
        sysArticle.setArticleTitle("我是谁");
        sysArticle.setArticleList("第三节课拉德斯基老实交代");
        sysArticle.setArticleImg("flsdjflsdjfd");
        Integer integer = sysArticleMapper.updateArticle(sysArticle);
        System.out.println(integer);
    }

    @Test
    void deleteByArticle() {
        Integer integer = sysArticleMapper.deleteByArticle(103);
        System.out.println(integer);
    }

    @Test
    void selectNameList() {
        List<ArticleVO> codetype = sysArticleMapper.selectNameList("codetype");
        System.out.println(codetype);
    }
}