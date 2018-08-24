package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 验证用户密码是否正确
     */
    User verifyUser(User user, HttpServletRequest request);

}
