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
    @RequestMapping(value = "/getSum", method = RequestMethod.GET)
    public ResultJson getInverterSum(@RequestParam("name") String name) {
        long sum = inverterService.getInverterTotal(name);
        return ResultJson.success(sum);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultJson getInverterData(@RequestParam("name") String name, PageQuery pageQuery) {
        List<Inverter> inverters = inverterService.getInverterData(name, pageQuery);
        return ResultJson.success(inverters);
    }

    @ResponseBody
    @RequestMapping(value = "/getPower", method = RequestMethod.GET)
    public ResultJson getInverterPower(@RequestParam("name") String name, @RequestParam("dateType") String dateType,
                                       @RequestParam("detailDate") String detailDate) {
        TreeMap<String, Double> resultJsonMap = inverterService.getInverterPower(name, dateType, detailDate);
        return ResultJson.success(resultJsonMap);
    }
}
