package com.codetype.web.blog.mapper;

import com.codetype.web.blog.dto.UserVO;
import com.codetype.web.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author bm
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2022-03-10 23:16:41
* @Entity com.codetype.web.blog.entity.User
*/

@Mapper
public interface UserMapper {
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
    Integer add(User user);

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
    Integer updateUserVo(User user);

}
