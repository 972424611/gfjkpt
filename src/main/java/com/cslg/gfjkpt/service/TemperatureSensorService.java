package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.TemperatureSensorParam;
import com.cslg.gfjkpt.model.TemperatureSensorInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TemperatureSensorService {
    //获取室内温度传感器实时变化
    public TemperatureSensorInfo[] getIndoorTemperatureSensorInfo(Date time);

    //获取室内温度变化图标，每过半个小时取一个点
    public List<TemperatureSensorParam> getIndoorTemperatureSensorChart(TemperatureSensorInfo[] temperatureSensorInfos);

    //获取当前室外温度湿度
    public TemperatureSensorInfo getCurrentOutdoorTemperatureSensorInfo();
}
