package com.codetype.web.blog.controller;

import com.codetype.utils.ResultVo;
import com.codetype.web.blog.entity.SysCarousel;
import com.codetype.web.blog.service.SysCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "轮播图")
@RestController
@RequestMapping("/api/getCarousel")
public class SysCarouselController {
    @Autowired
    private SysCarouselService sysCarouselService;

    /**
     * 查询
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    public ResultVo selectAllCarousel(){
        List<SysCarousel> sysCarousels = sysCarouselService.selectBySysUrl();
        return new ResultVo("",200,sysCarousels);
    }

    /**
     * 按id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "按照id查询")
    public ResultVo selectById(@PathVariable("id") Integer id){
        List<SysCarousel> sysCarousels = sysCarouselService.selectBySysId(id);
        return  new ResultVo("",200,sysCarousels);
    }
    /**
     * 新增
     * @param sysCarousel
     * @return
     */
    @PostMapping
    @ApiOperation(value ="新增")
    public ResultVo addCarousel(SysCarousel sysCarousel){
        Boolean aBoolean = sysCarouselService.addBySysUrl(sysCarousel);
        return new ResultVo("",200,aBoolean);
    }
    /**
     * 修改
     * @param sysCarousel
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改")
    public ResultVo updateCarousel(SysCarousel sysCarousel){
        Boolean aBoolean = sysCarouselService.updateSysUrl(sysCarousel);
        return new ResultVo("",200,aBoolean);
    }
    @DeleteMapping
    @ApiOperation(value = "删除")
    public ResultVo deleteCarousel(Integer id){
        Boolean aBoolean = sysCarouselService.deleteBySysUrl(id);
        return new ResultVo("",200,aBoolean);
    }
}
