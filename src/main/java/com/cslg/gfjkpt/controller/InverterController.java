package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.inverter.PredictParam;
import com.cslg.gfjkpt.vo.inverter.ChartVo;
import com.cslg.gfjkpt.vo.inverter.IconVo;
import com.cslg.gfjkpt.vo.inverter.InverterVo;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.vo.inverter.PredictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    public ResultJson chart(ChartParam chartParam) {
        List<ChartVo> chartVoList = inverterService.getInverterChart(chartParam);
        return ResultJson.success(chartVoList);
    }

    @ResponseBody
    @RequestMapping(value = "/icon")
    public ResultJson icon() {
        IconVo iconVo = inverterService.getInverterIcon();
        return ResultJson.success(iconVo);
    }

    @ResponseBody
    @RequestMapping(value = "/predict")
    public ResultJson predict(PredictParam predictParam) {
        PredictVo predictVo = inverterService.getInverterPredict(predictParam);
        return ResultJson.success(predictVo);
    }

}
