package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.InverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

/**
 * 逆变器Controller
 * @author Twilight
 */
@Controller
@RequestMapping("/inverter")
public class InverterController {

    @Autowired
    private InverterService inverterService;

    @ResponseBody
    @RequestMapping(value = "/getNames")
    public String getInverterNames() {
        List<String> inverterNameList = inverterService.getInverterNameList();
        return new ResultJson().returnJsonp(inverterNameList);
    }

    @ResponseBody
    @RequestMapping(value = "/getSum")
    public String getInverterSum(@RequestParam("name") String name) {
        long sum = inverterService.getInverterTotal(name);
        return new ResultJson().returnJsonp(sum);
        //return ResultJson.success(sum);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String getInverterData(@RequestParam("name") String name, PageQuery pageQuery) {
        List<Inverter> inverters = inverterService.getInverterData(name, pageQuery);
        return new ResultJson().returnJsonp(inverters);
        //return ResultJson.success(inverters);
    }

    @ResponseBody
    @RequestMapping(value = "/getPower")
    public String getInverterPower(@RequestParam("name") String name, @RequestParam("dateType") String dateType,
                                   @RequestParam("detailDate") String detailDate) {
        TreeMap<String, Double> resultJsonMap = inverterService.getInverterPower(name, dateType, detailDate);
        return new ResultJson().returnJsonp(resultJsonMap);
        //return ResultJson.success(resultJsonMap);
    }
}
