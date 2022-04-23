package com.codetype.web.blog.service;

import com.codetype.web.blog.dto.UserVO;
import com.codetype.web.blog.entity.User;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-03-10 23:16:41
*/

public interface UserService{
    /**
     * spring security认证时调用
     * @param username
     * @return
     */
    User getUsersName(String username);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();

    /**
     * 添加用户
     * @param user
     * @return
     */
    Boolean add(User user);

    /**
     * 查询用户基本信息
     * @return
     */
    List<UserVO> selectalluser();
    /**
     * 通过用户查询基本信息
     * @param username
     * @return
     */
    List<UserVO> selectUsername(String username);

    /**
     * 修改用户信息
     * @return
     */
    Boolean updateUserVo(User user);
}
