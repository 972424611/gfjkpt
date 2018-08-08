package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 获取用户通过id(但不获取密码)
     * @param id 用户id
     * @return User
     */
    User selectUserById(int id);

    /**
     * 获取用户通过用户名(但不获取密码)
     * @param username 用户名
     * @return User
     */
    User selectUserByUsername(@Param("username") String username);

    /**
     * 用来判断用户名密码是否正确
     * @param user User
     * @return 用户id,
     */
    Integer selectUserIdByAdmAndPw(User user);

    /**
     * 创建新用户
     * @param user User
     */
    void insertUser(User user);
}
