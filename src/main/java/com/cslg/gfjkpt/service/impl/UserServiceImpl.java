package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.exception.UserException;
import com.cslg.gfjkpt.mapper.UserMapper;
import com.cslg.gfjkpt.service.UserService;
import com.cslg.gfjkpt.utils.PropertyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void verifyUser(User user, HttpServletRequest request) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        Integer id = userMapper.selectUserIdByAdmAndPw(user);
        if(id == null) {
            throw new UserException("账号密码不正确");
        }
        user.setPassword(null);
        CookieSessionManage.setSession(request, user);
    }

    @Override
    public void saveUser(UserParam param) {
        BeanValidator.check(param);
        //判断用户是否已经存在
        Object object = userMapper.selectUserByUsername(param.getUsername());
        if(object != null) {
            throw new UserException("该用户已经存在");
        }
        User user = new User();
        BeanUtils.copyProperties(param, user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //md5加密, 使用spring自带的md5加密功能
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insertUser(user);
    }

}
