package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/poStaInfo")
    public ResultJson infoList(PageQuery pageQuery) {
        List<PowerStationInfo> resultList = adminService.getList(pageQuery);
        return ResultJson.success(resultList);
    }

    @ResponseBody
    @RequestMapping("/getSum")
    public ResultJson getSum() {
        long userSum = adminService.getUserTotal();
        return ResultJson.success(userSum);
    }
}
