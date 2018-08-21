package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.inverter.PredictParam;
import com.cslg.gfjkpt.utils.VeDateUtils;
import com.cslg.gfjkpt.vo.inverter.ChartVo;
import com.cslg.gfjkpt.vo.inverter.IconVo;
import com.cslg.gfjkpt.vo.inverter.InverterVo;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.vo.inverter.PredictVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        if("day".equals(chartParam.getType())) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(chartParam.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day - 1);
            chartParam.setDate(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        } else {
            try {
                date = new SimpleDateFormat("yyyy-MM").parse(chartParam.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            calendar.set(Calendar.MONTH, month - 1);
            chartParam.setDate(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }
        List<ChartVo> chartVoList = inverterService.getInverterChart(chartParam);
        if("day".equals(chartParam.getType())) {
            chartParam.setDate(VeDateUtils.getDayAfter(chartParam.getDate()));
        } else {
            try {
                date = new SimpleDateFormat("yyyy-MM").parse(chartParam.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            calendar.set(Calendar.MONTH, month + 1);
            chartParam.setDate(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }
        chartVoList.addAll(inverterService.getInverterChart(chartParam));
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
