package com.codetype.web.blog.service;

import com.codetype.web.blog.dto.ArticleVO;
import com.codetype.web.blog.dto.SearchVO;
import com.codetype.web.blog.entity.SysArticle;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_article(文章标题)】的数据库操作Service
* @createDate 2022-03-26 22:26:29
*/
public interface SysArticleService {
    /**
     * 查询文章基本信息
     * @return
     */
    List<SysArticle> selectArticle();
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysArticle> selectPageArticle(int pageNum, int pageSize);
    /**
     * 查询文章详情
     * @param id
     * @return
     */
    List<SysArticle> selectArticleId(Integer id);

    /**
     * 查询用户所写文章次数
     * @return
     */
    Integer selectFrequency(Integer id);
    /**
     * 添加文章
     * @return
     */
    Boolean addArticle(SysArticle sysArticle);

    /**
     * 封装用户查询博客列表
     * @return
     */
    List<ArticleVO> articleList();

    /**
     * 根据个人信息查询文章列表
     * @param username
     * @return
     */
    List<ArticleVO> selectNameList(String username);

    /**
     * 修改文章
     */
    Boolean updateArticle(SysArticle sysArticle);

    /**
     * 删除文章
     */
    Boolean deleteByArticle(Integer id);

    /**
     * 模糊查询
     */
    List<SearchVO> searchList(String articleName);

}
