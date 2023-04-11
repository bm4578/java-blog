package com.codetype.web.blog.controller;

import com.codetype.utils.ResultVo;
import com.codetype.web.blog.dto.ArticleVO;
import com.codetype.web.blog.dto.SearchVO;
import com.codetype.web.blog.entity.SysAnnouncement;
import com.codetype.web.blog.entity.SysArticle;
import com.codetype.web.blog.entity.SysCarousel;
import com.codetype.web.blog.service.SysAnnouncementService;
import com.codetype.web.blog.service.SysArticleService;
import com.codetype.web.blog.service.SysCarouselService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "博客中心")
@RestController
@RequestMapping("/api/article")
public class SysArticleController {
    @Autowired
    private SysArticleService sysArticleService;

    @Autowired
    private SysCarouselService sysCarouselService;

    @Autowired
    private SysAnnouncementService sysAnnouncementService;
    /**
     * 查询所有
     */
    @ApiOperation(value = "分页所有")
    @GetMapping("/list/page/{pageNum}/{pageSize}")
    public PageInfo<SysArticle> list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        PageInfo<SysArticle> sysArticlePageInfo = sysArticleService.selectPageArticle(pageNum, pageSize);
        return sysArticlePageInfo;
    }
    @ApiOperation(value = "查询所有")
    @GetMapping("/list")
    public ResultVo list(){
        List<SysArticle> sysArticles = sysArticleService.selectArticle();
        return new ResultVo("",200,sysArticles);
    }
    /**
     * 查询个人文章
     */
    @ApiOperation(value = "查询个人文章")
    @GetMapping("{username}")
    public ResultVo selectName(@PathVariable("username") String username){
        List<ArticleVO> articleVOS = sysArticleService.selectNameList(username);
        return new ResultVo("",200,articleVOS);
    }
    /**
     * 查询详情
     */
    @ApiOperation(value = "查看详情")
    @PostMapping("/list/{id}")
    public ResultVo page(@PathVariable("id") Integer id){
        List<SysArticle> sysArticles = sysArticleService.selectArticleId(id);
        return new ResultVo("",200,sysArticles);
    }

    /**
     * 获取作者文章篇数
     * @return
     */
    @ApiOperation(value = "获取作者文章篇数")
    @PutMapping("/list/userNum/{id}")
    public ResultVo ArticlesUserNum(@PathVariable("id") Integer id){
        Integer sysArticles = sysArticleService.selectFrequency(id);
        return new ResultVo("",200,sysArticles);
    }

    /**
     * 添加文章
     * @param sysArticle
     * @return
     */
    @ApiOperation(value = "添加文章")
    @PostMapping("/list/addArticle")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "articleTitle", value = "文章标题",required = true),
            @ApiImplicitParam(dataType = "String",name = "articleList", value = "文章内容",required = true),
            @ApiImplicitParam(dataType = "String",name = "articleImg", value = "文章内容",required = true),
            @ApiImplicitParam(dataType = "Integer",name = "articleId", value = "文章ID",required = true),
    }
    )
    public ResultVo addArticle(SysArticle sysArticle){
        Boolean aBoolean = sysArticleService.addArticle(sysArticle);
        return new ResultVo("",200,aBoolean);
    }
    /**
     * 封装用户查询博客列表
     */
    @ApiOperation(value = "查询用户博客列表")
    @GetMapping("/list/articleList")
    public ResultVo articleList(){
        List<ArticleVO> articleVOS = sysArticleService.articleList();
        return new ResultVo("",200,articleVOS);
    }
    /**
     * 封装用户查询博客列表
     */
    @ApiOperation(value = "模糊搜索")
    @GetMapping("/list/searchList")
    public ResultVo searchList(String searchName){
        List<SearchVO> articleVOS = sysArticleService.searchList(searchName);
        return new ResultVo("",200,articleVOS);
    }

    /**
     * 编辑博客
     * @param sysArticle
     * @return
     */
    @ApiOperation(value = "编辑博客")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "articleTitle", value = "文章标题",required = true),
            @ApiImplicitParam(dataType = "String",name = "articleList", value = "文章内容",required = true),
            @ApiImplicitParam(dataType = "String",name = "articleImg", value = "文章内容",required = true),
            @ApiImplicitParam(dataType = "Integer",name = "articleId", value = "文章ID",required = true),
    }
    )
    @PostMapping("/list/updateArticle")
    public ResultVo articleUpdate(SysArticle sysArticle){
        Boolean aBoolean = sysArticleService.updateArticle(sysArticle);
        return new ResultVo("",200,aBoolean);
    }
    /**
     * 删除博客
     */
    @ApiOperation(value = "删除博客")
    @ApiImplicitParam(dataType = "int",name = "id", value = "文章ID",required = true)
    @GetMapping("/list/deleteArticle")
    public ResultVo delectArticle(Integer id){
        Boolean aBoolean = sysArticleService.deleteByArticle(id);
        return new ResultVo("",200,aBoolean);
    }
    @ApiOperation(value = "获取轮播图")
    @GetMapping("/list/getCarousel")
    public ResultVo getCarousel(){
        List<SysCarousel> sysCarousels = sysCarouselService.selectBySysUrl();
        return new ResultVo("",200,sysCarousels);
    }
    @ApiOperation(value = "获取公告")
    @ApiImplicitParam(dataType = "int",name = "id", value = "ID",required = true)
    @GetMapping("/list/getCarousel/{id}")
    public ResultVo getAnnouncement(@PathVariable("id") Integer id){
        SysAnnouncement sysAnnouncement = sysAnnouncementService.selectByPrimaryKey(id);
        return new ResultVo("",200,sysAnnouncement);
    }


}
