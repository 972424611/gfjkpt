package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /*@ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getLoadByDate(@RequestParam("name") String name, @RequestParam("dateType") String dateType,
                                @RequestParam("detailDate") String detailDate,
                                HttpServletRequest request) {
        TreeMap<String, Double> resultMap = loadService.getLoadDataByDate(name, dateType, detailDate);
        ResultJson resultJson = new ResultJson(resultMap);
        String json = JsonUtils.objectToJson(resultJson);
        return request.getParameter("callback") + "(" + json + ")";
    }*/

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getLoadByDate(@RequestParam("name") String name, @RequestParam("dateType") String dateType,
                                @RequestParam("detailDate") String detailDate) {
        TreeMap<String, Double> resultMap = loadService.getLoadDataByDate(name, dateType, detailDate);
        //return ResultJson.success(resultMap);
        return new ResultJson().returnJsonp(resultMap);
    }
}
