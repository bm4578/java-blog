package com.codetype.web.blog.controller;

import com.codetype.utils.ResultVo;
import com.codetype.web.blog.entity.SysAnnouncement;
import com.codetype.web.blog.service.SysAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/SysAnnouncement")
public class SysAnnouncementController {
    @Autowired
    private SysAnnouncementService sysAnnouncementService;

    /**
     * 根据属性查询 （当参数为空时候）查询所有
     * @param sysAnnouncement
     * @return
     */
    @GetMapping
    public ResultVo query(){
        List<SysAnnouncement> sysAnnouncements = sysAnnouncementService.selectAll();
        return new ResultVo("",200,sysAnnouncements);
    }

    /**
     * 按照id查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResultVo queryId(@PathVariable("id") Integer id){
        SysAnnouncement sysAnnouncement = sysAnnouncementService.selectByPrimaryKey(id);
        return new ResultVo("",200,sysAnnouncement);
    }

    /**
     * 插入数据
     * @param sysAnnouncement
     * @return
     */
    @PostMapping
    public ResultVo add(@RequestBody SysAnnouncement sysAnnouncement){
        Boolean insert = sysAnnouncementService.insert(sysAnnouncement);
        return new ResultVo("",200,insert);
    }

    /**
     * 根据主键更新数据
     * @param sysAnnouncement
     * @return
     */

    @PutMapping
    public ResultVo update(SysAnnouncement sysAnnouncement){
        Boolean aBoolean = sysAnnouncementService.updateByPrimaryKey(sysAnnouncement);
        return new ResultVo("",200,aBoolean);
    }
    /**
     * 根据主键删除
     * @param sysId
     * @return
     */
    @DeleteMapping
    public ResultVo delete(@PathVariable("sysId") Integer sysId){
        Boolean aBoolean = sysAnnouncementService.deleteByPrimaryKey(sysId);
        return new ResultVo("",200,aBoolean);
    }
}
