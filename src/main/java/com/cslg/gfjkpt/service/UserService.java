package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 验证用户密码是否正确
     */
    void verifyUser(User user, HttpServletRequest request);

    /**
     * 保存新用户
     * @param param UserParam
     */
    void saveUser(UserParam param);
}
