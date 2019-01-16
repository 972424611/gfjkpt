package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.common.SocketConnector;
import com.cslg.gfjkpt.service.AdminService;
import com.cslg.gfjkpt.utils.CodeUtil;
import com.cslg.gfjkpt.vo.UserVo;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultJson add(UserParam userParam, HttpServletRequest request) {
        String username = (String) CookieSessionManage.getSession(request);
        if("管理员".equals(username)) {
            adminService.addUser(userParam);
        }
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public ResultJson list(HttpServletRequest request) {
        List<UserVo> userList = adminService.getUsers();
        return ResultJson.success(userList);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultJson delete(@RequestParam("id") Integer id, HttpServletRequest request) {
        String username = (String) CookieSessionManage.getSession(request);
        if("管理员".equals(username)) {
            adminService.deleteUserById(id);
        }
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/sendCommand")
    public ResultJson sendCommand(String cmd){
        try {
            boolean b=SocketConnector.sentMsg(cmd);
            if (b){
                return ResultJson.success();
            }
        }catch (Exception e){
            return ResultJson.fail("服务端异常!");
        }
        return ResultJson.fail("发送失败");
    }
}
