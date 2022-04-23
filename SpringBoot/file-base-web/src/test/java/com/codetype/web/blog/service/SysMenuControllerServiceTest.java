package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.SysMenu;
import com.codetype.web.blog.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysMenuControllerServiceTest {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Test
    void selectMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenu(0);
        System.out.println(sysMenus);
    }
}