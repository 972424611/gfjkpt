package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.inverter.CVParam;
import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.inverter.PredictParam;
import com.cslg.gfjkpt.utils.VeDateUtils;
import com.cslg.gfjkpt.vo.inverter.*;
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
    public ResultJson chart(ChartParam chartParam) {
        List<ChartVo> chartVoList = inverterService.getInverterChart(chartParam);
        if("day".equals(chartParam.getType())) {
            chartParam.setDate(VeDateUtils.getDayBefore(chartParam.getDate()));
        } else if("month".equals(chartParam.getType())) {
            chartParam.setDate(VeDateUtils.getMonthBefore(chartParam.getDate()));
        } else {
            return ResultJson.success(chartVoList);
        }
        List<ChartVo> beforeChartVoList = inverterService.getInverterChart(chartParam);
        beforeChartVoList.addAll(chartVoList);
        return ResultJson.success(beforeChartVoList);
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

    @ResponseBody
    @RequestMapping(value = "/cvChart")
    public ResultJson cvChart(CVParam cvParam) {
        CVChartVo cvChartVo = inverterService.getCVChart(cvParam);
        return ResultJson.success(cvChartVo);

    }
}
