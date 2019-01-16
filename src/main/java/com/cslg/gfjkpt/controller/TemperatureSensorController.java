package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.TemperatureSensorParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.model.TemperatureSensorInfo;
import com.cslg.gfjkpt.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展示温度传感器
 */
@Controller
public class TemperatureSensorController {
    @Autowired
    TemperatureSensorService temperatureSensorService;

    @ResponseBody
    @RequestMapping("/getIndoorTemperatureSensorInfo")
    public ResultJson getIndoorTemperatureSensorInfo(@DateTimeFormat(pattern = "yyyy-MM-dd") Date time){
       try{
           TemperatureSensorInfo[] temperatureSensorInfos=temperatureSensorService.getIndoorTemperatureSensorInfo(time);
           //整理数据并返回
           List<TemperatureSensorParam> temperatureSensorParams=temperatureSensorService.getIndoorTemperatureSensorChart(temperatureSensorInfos);
           return ResultJson.success(temperatureSensorParams);
       }catch (Exception e){
           return ResultJson.fail(null);
       }
    }

    @ResponseBody
    @RequestMapping("/getCurrentOutdoorTemperatureSensorInfo")
    public ResultJson getCurrentOutdoorTemperatureSensorInfo(){
        try {
            TemperatureSensorInfo temperatureSensorInfo=temperatureSensorService.getCurrentOutdoorTemperatureSensorInfo();
            Map<String,Double> map=new HashMap<>();
            map.put("outdoor_temperature",temperatureSensorInfo.getTemperature());
            map.put("outdoor_humidity",temperatureSensorInfo.getHumidity());
            return ResultJson.success(map);
        }catch (Exception e){
            return ResultJson.fail(null);
        }

    }

}
