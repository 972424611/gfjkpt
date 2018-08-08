/*
package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.exception.UserException;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.UserService;
import com.cslg.gfjkpt.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

*/
/**
 * 用户模块
 * @author aekc
 *//*

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public String userLogin(User user, HttpServletRequest request) {
        String errorMsg = "";
        if(StringUtils.isBlank(user.getUsername())) {
            errorMsg = "用户名不能为空";
        }else if(StringUtils.isBlank(user.getPassword())) {
            errorMsg = "密码不能为空";
        }else {
            try {
                userService.verifyUser(user, request);
                if("admin".equals(user.getUsername())) {
                    return new ResultJson().returnJsonp("admin", request);
                }
                return new ResultJson().returnJsonp("", request);
               // return ResultJson.success();
            } catch (UserException e) {
               // return ResultJson.fail(e.getMessage());
                return new ResultJson().returnJsonp(null);
            }
        }
        //return ResultJson.fail(errorMsg);
        return new ResultJson().returnJsonp(null);
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public ResultJson userRegister(UserParam param) {
        try {
            userService.saveUser(param);
        } catch (UserException e) {
            return ResultJson.fail(e.getMessage());
        }
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public ResultJson userLogout(HttpServletRequest request, HttpServletResponse response) {
        CookieSessionManage.clearCookieAndSession(request, response);
        return ResultJson.success();
    }

}
*/
