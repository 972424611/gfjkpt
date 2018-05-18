package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.LoadChartParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.vo.LoadChartVo;
import com.cslg.gfjkpt.service.LoadService;
import com.cslg.gfjkpt.vo.LoadIconVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

/**
 * 负荷Controller
 * @author Twilight
 */
@Controller
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @ResponseBody
    @RequestMapping(value = "/chart")
    public ResultJson chart(LoadChartParam loadChartParam) {
        List<LoadChartVo> loadChartVoList = loadService.getLoadChart(loadChartParam);
        return ResultJson.success(loadChartVoList);
    }

    @ResponseBody
    @RequestMapping("/loadIcon")
    public  ResultJson loadIcon() {
        LoadIconVo loadIconVo = loadService.getLoadIcon();
        return ResultJson.success(loadIconVo);
    }
}
