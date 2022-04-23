package com.codetype.web.blog.service.impl;

import com.codetype.web.blog.entity.SysMenu;
import com.codetype.web.blog.mapper.SysMenuMapper;
import com.codetype.web.blog.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2022-04-03 21:10:23
*/
@Service
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 菜单查询
     *
     * @param id
     * @return
     */
    @Override
    public List<SysMenu> selectMenu(Integer id) {
        return this.sysMenuMapper.selectMenu(id);
    }

    /**
     * 通过权限查询菜单
     *
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public List<SysMenu> selectMenuPermission(Boolean uid, Integer tid) {
        return this.sysMenuMapper.selectMenuPermission(uid,tid);
    }

}
