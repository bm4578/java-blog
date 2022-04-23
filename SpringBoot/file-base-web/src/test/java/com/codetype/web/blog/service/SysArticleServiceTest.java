package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.SysArticle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SysArticleServiceTest {
@Autowired
private SysArticleService sysArticleService;
    @Test
    void selectArticle() {
        System.out.println(sysArticleService.selectPageArticle(2, 5));
    }

    @Test
    void selectArticleId() {
        List<SysArticle> sysArticles = sysArticleService.selectArticleId(2);
        System.out.println(sysArticles);
    }

    /**
     * 查询指定用户所写的文章篇数
     */
    @Test
    void selectFrequency() {
        Integer integer = sysArticleService.selectFrequency(1);
        System.out.println(integer);
    }

    @Test
    void testSelectArticle() {
    }

    @Test
    void testSelectArticleId() {
    }

    @Test
    void testSelectFrequency() {
    }

    @Test
    void addArticle() {
        SysArticle sysArticle = new SysArticle();
        sysArticle.setUid(3);
        sysArticle.setArticleTitle("测试");
        sysArticle.setArticleList("测试");
        sysArticle.setArticleImg("");
        sysArticleService.addArticle(sysArticle);
    }

    @Test
    void testSelectArticle1() {
    }

    @Test
    void testSelectArticleId1() {
    }

    @Test
    void testSelectFrequency1() {
    }

    @Test
    void testAddArticle() {
    }
}