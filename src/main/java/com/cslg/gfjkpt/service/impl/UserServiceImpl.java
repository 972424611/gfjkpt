package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.exception.UserException;
import com.cslg.gfjkpt.mapper.UserMapper;
import com.cslg.gfjkpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User verifyUser(User user, HttpServletRequest request) {
        User u = userMapper.selectUserIdByAdmAndPw(user);
        if(u == null) {
            throw new UserException("账号密码不正确");
        }
        return u;
    }

}
