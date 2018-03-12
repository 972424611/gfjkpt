package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/poStaInfo", produces = "text/json;charset=UTF-8")
    public String infoList(PageQuery pageQuery) {
        List<PowerStationInfo> resultList = adminService.getList(pageQuery);
        //return ResultJson.success(resultList);
        return new ResultJson().returnJsonp(resultList);
    }

    @ResponseBody
    @RequestMapping("/getSum")
    public String getSum() {
        long userSum = adminService.getUserTotal();
        //return ResultJson.success(userSum);
        return new ResultJson().returnJsonp(userSum);
    }

    @ResponseBody
    @RequestMapping("/getUsers")
    public String getUsers(PageQuery pageQuery) {
        List<User> userList = adminService.getUserList(pageQuery);
        return new ResultJson().returnJsonp(userList);
    }

    @ResponseBody
    @RequestMapping("/checkUser")
    public String checkUser(@RequestParam("id") Integer id, HttpServletResponse response) {
        adminService.visitUser(id, response);
        return new ResultJson().returnJsonp("");
    }
}
