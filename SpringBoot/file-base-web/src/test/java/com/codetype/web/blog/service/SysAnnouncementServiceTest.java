package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.SysAnnouncement;
import com.codetype.web.blog.mapper.SysAnnouncementMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysAnnouncementServiceTest {
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
    }

    @Test
    void updateByPrimaryKey() {
        SysAnnouncement sysAnnouncement = new SysAnnouncement();
        sysAnnouncement.setSysId(1);
        sysAnnouncement.setSysText("jdkfkljdlk");
        sysAnnouncementMapper.updateByPrimaryKey(sysAnnouncement);
    }
}