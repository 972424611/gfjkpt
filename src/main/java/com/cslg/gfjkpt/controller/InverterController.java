package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.vo.InverterChartVo;
import com.cslg.gfjkpt.vo.InverterIconVo;
import com.cslg.gfjkpt.vo.InverterVo;
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
        InverterVo inverterVo = inverterService.getInverterData(name, pageQuery);
        return ResultJson.success(inverterVo);
    }

    @ResponseBody
    @RequestMapping(value = "/chart")
    public ResultJson chart(InverterChartParam inverterChartParam) {
        List<InverterChartVo> inverterChartVoList = inverterService.getInverterChart(inverterChartParam);
        return ResultJson.success(inverterChartVoList);
    }

    @ResponseBody
    @RequestMapping("/inverterIcon")
    public  ResultJson loadIcon() {
        InverterIconVo inverterIconVo = inverterService.getInverterIcon();
        return ResultJson.success(inverterIconVo);
    }
}
