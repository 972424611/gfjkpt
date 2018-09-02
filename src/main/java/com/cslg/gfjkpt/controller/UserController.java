package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.exception.UserException;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public ResultJson userLogin(User user, HttpServletRequest request) {
        String errorMsg;
        if(StringUtils.isBlank(user.getUsername())) {
            errorMsg = "用户名不能为空";
        } else if(StringUtils.isBlank(user.getPassword())) {
            errorMsg = "密码不能为空";
        } else {
            try {
                User u = userService.verifyUser(user, request);
                CookieSessionManage.setSession(request, u.getRole());
                if("管理员".equals(u.getRole())) {
                    return ResultJson.success("admin");
                }
                return ResultJson.success();
            } catch (UserException e) {
                return ResultJson.fail(e.getMessage());
            }
        }
        return ResultJson.fail(errorMsg);
    }
}
