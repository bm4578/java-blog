package com.codetype.web.blog.controller;

import com.codetype.utils.ResultVo;
import com.codetype.web.blog.entity.SysCarousel;
import com.codetype.web.blog.service.SysCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "轮播图")
@RestController
@RequestMapping("/api/getCarousel")
public class SysCarouselController {
    @Autowired
    private SysCarouselService sysCarouselService;

    /**
     * 查询
     * @return
     */
    @ApiOperation(value = "查询所有", notes = "查询", httpMethod = "GET")
    @GetMapping
    public ResultVo selectAllCarousel(){
        List<SysCarousel> sysCarousels = sysCarouselService.selectBySysUrl();
        return new ResultVo("",200,sysCarousels);
    }

    /**
     * 按id查询
     * @param id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "按照id查询", notes = "按id查询", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResultVo selectById(@PathVariable("id") Integer id){
        List<SysCarousel> sysCarousels = sysCarouselService.selectBySysId(id);
        return  new ResultVo("",200,sysCarousels);
    }
    /**
     * 新增
     * @param sysCarousel
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "SysCarousel", name = "sysCarousel", value = "", required = true)
    })
    @ApiOperation(value = "新增", notes = "新增", httpMethod = "POST")
    @PostMapping
    public ResultVo addCarousel(SysCarousel sysCarousel){
        Boolean aBoolean = sysCarouselService.addBySysUrl(sysCarousel);
        return new ResultVo("",200,aBoolean);
    }
    /**
     * 修改
     * @param sysCarousel
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "SysCarousel", name = "sysCarousel", value = "", required = true)
    })
    @ApiOperation(value = "修改", notes = "修改", httpMethod = "PUT")
    @PutMapping
    public ResultVo updateCarousel(SysCarousel sysCarousel){
        Boolean aBoolean = sysCarouselService.updateSysUrl(sysCarousel);
        return new ResultVo("",200,aBoolean);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "删除", notes = "", httpMethod = "DELETE")
    @DeleteMapping
    public ResultVo deleteCarousel(Integer id){
        Boolean aBoolean = sysCarouselService.deleteBySysUrl(id);
        return new ResultVo("",200,aBoolean);
    }
}
