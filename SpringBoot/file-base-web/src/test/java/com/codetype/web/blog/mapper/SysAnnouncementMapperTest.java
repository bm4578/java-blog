package com.codetype.web.blog.mapper;

import com.codetype.web.blog.entity.SysAnnouncement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysAnnouncementMapperTest {
@Autowired
private SysAnnouncementMapper sysAnnouncementMapper;
    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void selectAll() {
        List<SysAnnouncement> sysAnnouncements = sysAnnouncementMapper.selectAll();
        System.out.println(sysAnnouncements);
    }

    @Test
    void updateByPrimaryKey() {
        SysAnnouncement sysAnnouncement = new SysAnnouncement();
        sysAnnouncement.setSysId(1);
        sysAnnouncement.setSysText("hhhhh");
        int i = sysAnnouncementMapper.updateByPrimaryKey(sysAnnouncement);
        System.out.println(i+"======================");
    }
}