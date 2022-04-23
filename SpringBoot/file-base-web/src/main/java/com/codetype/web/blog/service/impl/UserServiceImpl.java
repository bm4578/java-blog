package com.codetype.web.blog.service.impl;

import com.codetype.web.blog.dto.UserVO;
import com.codetype.web.blog.entity.User;
import com.codetype.web.blog.service.UserService;
import com.codetype.web.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-03-10 23:16:41
*/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * spring security认证时调用
     *
     * @param username
     * @return
     */
    @Override
    public User getUsersName(String username) {
        return this.userMapper.getUsersName(username);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<User> selectAll() {
        return this.userMapper.selectAll();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean add(User user) {
        return this.userMapper.add(user) > 0;
    }

    /**
     * 查询用户基本信息
     *
     * @return
     */
    @Override
    public List<UserVO> selectalluser() {
        return this.userMapper.selectalluser();
    }

    /**
     * 通过用户查询基本信息
     *
     * @param username
     * @return
     */
    @Override
    public List<UserVO> selectUsername(String username) {
        return this.userMapper.selectUsername(username);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Boolean updateUserVo(User user) {
        return this.userMapper.updateUserVo(user) > 0;
    }
}
