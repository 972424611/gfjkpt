package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.AdminService;
import com.cslg.gfjkpt.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultJson add(UserParam userParam) {
        adminService.addUser(userParam);
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public ResultJson list() {
        List<UserVo> userList = adminService.getUsers();
        return ResultJson.success(userList);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultJson delete(@RequestParam("id") Integer id) {
        adminService.deleteUserById(id);
        return ResultJson.success();
    }
}
