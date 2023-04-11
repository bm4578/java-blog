package com.codetype.web.blog.mapper;

import com.codetype.web.blog.dto.SearchVO;
import com.codetype.web.blog.entity.SysArticle;
import com.codetype.web.blog.dto.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_article(文章标题)】的数据库操作Mapper
* @createDate 2022-03-26 22:26:29
* @Entity com.codetype.blog.entity.SysArticle
*/
@Mapper
public interface SysArticleMapper {
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

    List<SysArticle> selectPageArticle(int pageNum,int pageSize);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    List<SysArticle> selectArticleId(Integer id);
    /**
     * 查询文章详情
     * @param id
     * @return
     */
    Integer selectFrequency(Integer id);

    /**
     * 添加文章
     */
    Integer addArticle(SysArticle sysArticle);

    /**
     * 封装用户查询博客列表
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
   Integer updateArticle(SysArticle sysArticle);

    /**
     * 删除文章
     */
    Integer deleteByArticle(Integer id);

    /**
     * 模糊查询
     */
    List<SearchVO> searchList(String articleName);
}
