package com.codetype.web.blog.mapper;

import com.codetype.web.blog.dto.UserVO;
import com.codetype.web.blog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUsersName() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void add() {
    }

    @Test
    void selectalluser() {
        List<UserVO> selectalluser = userMapper.selectalluser();
        System.out.println(selectalluser);
    }

    @Test
    void updateUserVo() {
        User user = new User();
        user.setId(2L);
        user.setUsername("test");
        user.setIsEnabled(true);
        Integer integer = userMapper.updateUserVo(user);
        System.out.println(integer);
    }

    @Test
    void selectUsername() {
        List<UserVO> codetype = userMapper.selectUsername("codetype");
        System.out.println(codetype);
    }
}