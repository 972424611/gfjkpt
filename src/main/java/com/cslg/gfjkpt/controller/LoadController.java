package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.load.ChartParam;
import com.cslg.gfjkpt.beans.load.ContrastChartParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.LoadService;

import com.cslg.gfjkpt.vo.load.ChartVo;
import com.cslg.gfjkpt.vo.load.IconVo;
import com.cslg.gfjkpt.vo.load.PieChartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 负荷Controller
 * @author Twilight
 */
@Controller
@RequestMapping("/load")
public class LoadController {

    private final LoadService loadService;

    @Autowired
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @ResponseBody
    @RequestMapping(value = "/chart")
    public ResultJson chart(ChartParam chartParam) {
        List<ChartVo> loadChartVoList = loadService.getLoadChart(chartParam);
        return ResultJson.success(loadChartVoList);
    }

    @ResponseBody
    @RequestMapping("/icon")
    public  ResultJson icon(@RequestParam("local") String local) {
        IconVo loadIconVo = loadService.getLoadIcon(local);
        return ResultJson.success(loadIconVo);
    }

    @ResponseBody
    @RequestMapping(value = "/contrastChart")
    public ResultJson contrastChart(ContrastChartParam contrastChartParam) {
        return ResultJson.success(loadService.getContrastChart(contrastChartParam));
    }

    @ResponseBody
    @RequestMapping(value = "/pieChart")
    public ResultJson pieChart() {
        List<PieChartVo> pieChartVoList = loadService.getPieChart();
        return ResultJson.success(pieChartVoList);
    }
}
