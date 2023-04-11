package com.codetype.web.blog.service.impl;


import com.codetype.web.blog.dto.ArticleVO;
import com.codetype.web.blog.dto.SearchVO;
import com.codetype.web.blog.entity.SysArticle;
import com.codetype.web.blog.service.SysArticleService;
import com.codetype.web.blog.mapper.SysArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author bm
* @description 针对表【sys_article(文章标题)】的数据库操作Service实现
* @createDate 2022-03-26 22:26:29
*/
@Service
public class SysArticleServiceImpl
implements SysArticleService{
    @Autowired
    private SysArticleMapper sysArticleMapper;

    /**
     * 查询文章基本信息
     *int pageNum, int pageSize
     * @return
     */
    @Override
    public List<SysArticle> selectArticle() {
        List<SysArticle> sysArticles = sysArticleMapper.selectArticle();
        return sysArticles;
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SysArticle> selectPageArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysArticle> sysArticles = sysArticleMapper.selectPageArticle(pageNum, pageSize);
        PageInfo<SysArticle> sysArticlePageInfo = new PageInfo<>(sysArticles);
        return sysArticlePageInfo;
    }

    /**
     * 查询文章详情
     *
     * @param id
     * @return
     */
    @Override
    public List<SysArticle> selectArticleId(Integer id) {
        return this.sysArticleMapper.selectArticleId(id);
    }

    /**
     * 查询用户所写文章次数
     *
     * @return
     */
    @Override
    public Integer selectFrequency(Integer id) {
        return this.sysArticleMapper.selectFrequency(id);
    }

    /**
     * 添加文章
     *
     * @param sysArticle
     * @return
     */
    @Override
    public Boolean addArticle(SysArticle sysArticle) {
        return sysArticleMapper.addArticle(sysArticle) > 0;
    }

    /**
     * 封装用户查询博客列表
     * @return
     */
    @Override
    public List<ArticleVO> articleList() {
        return this.sysArticleMapper.articleList();
    }

    /**
     * 根据个人信息查询文章列表
     *
     * @param username
     * @return
     */
    @Override
    public List<ArticleVO> selectNameList(String username) {
        return this.sysArticleMapper.selectNameList(username);
    }

    /**
     * 修改文章
     *
     * @param sysArticle
     */
    @Override
    public Boolean updateArticle(SysArticle sysArticle) {
        return sysArticleMapper.updateArticle(sysArticle) > 0 ;
    }

    /**
     * 删除文章
     *
     * @param id
     */
    @Override
    public Boolean deleteByArticle(Integer id) {
        return sysArticleMapper.deleteByArticle(id) > 0 ;
    }

    /**
     * 模糊查询
     *
     * @param articleName
     */
    @Override
    public List<SearchVO> searchList(String articleName) {
        return sysArticleMapper.searchList(articleName);
    }
}
