package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.dto.InverterChartDto;
import com.cslg.gfjkpt.dto.InverterDto;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.InverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 逆变器Controller
 * @author Twilight
 */
@Controller
@RequestMapping("/inverter")
public class InverterController {

    private final InverterService inverterService;

    @Autowired
    public InverterController(InverterService inverterService) {
        this.inverterService = inverterService;
    }

    @ResponseBody
    @RequestMapping(value = "/getNames")
    public String getNames() {
        List<String> inverterNameList = inverterService.getInverterNameList();
        return new ResultJson().returnJsonp(inverterNameList);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public ResultJson list(@RequestParam("name") String name, PageQuery pageQuery) {
        InverterDto inverterDto = inverterService.getInverterData(name, pageQuery);
        return ResultJson.success(inverterDto);
    }

    @ResponseBody
    @RequestMapping(value = "/chart")
    public ResultJson chart(InverterChartParam inverterChartParam) {
        List<InverterChartDto> inverterChartDtoList = inverterService.getInverterChart(inverterChartParam);
        return ResultJson.success(inverterChartDtoList);
    }
}
