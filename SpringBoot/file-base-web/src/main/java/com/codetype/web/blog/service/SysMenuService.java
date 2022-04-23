package com.codetype.web.blog.service;


import com.codetype.web.blog.entity.SysMenu;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2022-04-03 21:10:23
*/
public interface SysMenuService{
    /**
     * 菜单查询
     * @param id
     * @return
     */
    List<SysMenu> selectMenu(Integer id);

    /**
     * 通过权限查询菜单
     * @param uid
     * @param tid
     * @return
     */
    List<SysMenu> selectMenuPermission(Boolean uid, Integer tid);

}
