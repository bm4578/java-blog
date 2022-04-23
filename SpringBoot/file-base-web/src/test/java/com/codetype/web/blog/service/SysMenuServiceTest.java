package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysMenuServiceTest {
@Autowired
private SysMenuService sysMenuService;
    @Test
    void selectMenuPermission() {
        List<SysMenu> sysMenus = sysMenuService.selectMenuPermission(true, 2);
        System.out.println(sysMenus);
    }
}