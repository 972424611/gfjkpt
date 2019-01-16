package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.ControllableSocketParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.model.ControllableSocketInfo;
import com.cslg.gfjkpt.service.ControllableSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 可控插座
 */
@Controller
public class ControllableSocketController {
    @Autowired
    ControllableSocketService controllableSocketService;
    @ResponseBody
    @RequestMapping("/getControllableSocketInfo")
    public ResultJson getControllableSocket(@DateTimeFormat(pattern = "yyyy-MM-dd") Date time){
        try {
            ControllableSocketInfo[] controllableSocketInfos=controllableSocketService.selectControllableSocketInfo(time);
            List<ControllableSocketParam> controllableSocketParamList=controllableSocketService.getControllableSocketParam(controllableSocketInfos);
            return ResultJson.success(controllableSocketParamList);
        }catch (Exception e){
            return ResultJson.fail(null);
        }

    }
}
