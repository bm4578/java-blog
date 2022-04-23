package com.codetype.web.blog.mapper;

import com.codetype.web.blog.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysMenuMapperTest {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    void selectMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenu(0);
        System.out.println(sysMenus);
    }

    @Test
    void selectMenuPermission() {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuPermission(true, 1);
        System.out.println(sysMenus);
    }

    @Test
    void testSelectMenuPermission() {
    }
}