package com.codetype.web.blog.controller;

import com.codetype.utils.ResultVo;
import com.codetype.web.blog.entity.SysMenu;
import com.codetype.web.blog.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list")
@Api(tags = "菜单中心")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @ApiOperation(value = "菜单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "Integer",name = "id", value = "权限ID",required = true),
    }
    )
    @GetMapping("/menu/{id}")
    public ResultVo selectMenu(@PathVariable("id") Integer id){
        List<SysMenu> sysMenuControllers = sysMenuService.selectMenu(id);
        return new ResultVo("",200,sysMenuControllers);
    }
    @ApiOperation(value = "权限分类菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "Boolean",name = "uid", value = "权限ID",required = true),
            @ApiImplicitParam(dataType = "Integer",name = "tid", value = "分类ID",required = true),
    }
    )
    @GetMapping("/menu/{uid}/{tid}")
    public ResultVo selectMenuPermission(@PathVariable("uid") Boolean uid,@PathVariable("tid") Integer tid){
        List<SysMenu> sysMenuControllers = sysMenuService.selectMenuPermission(uid,tid);
        return new ResultVo("",200,sysMenuControllers);
    }
}
