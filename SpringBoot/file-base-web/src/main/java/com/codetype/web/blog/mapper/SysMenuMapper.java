package com.codetype.web.blog.mapper;

import com.codetype.web.blog.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author bm
* @description 针对表【sys_menu】的数据库操作Mapper
* @createDate 2022-04-03 21:10:23
* @Entity com.codetype.web.blog.entity.SysMenu
*/
@Mapper
public interface SysMenuMapper {
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
    List<SysMenu> selectMenuPermission(@Param("uid")Boolean uid,@Param("tid") Integer tid);
}
