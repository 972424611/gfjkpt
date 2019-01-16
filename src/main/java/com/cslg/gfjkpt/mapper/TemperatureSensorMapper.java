package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.TemperatureSensorInfo;

import java.util.Date;


public interface TemperatureSensorMapper {
    public TemperatureSensorInfo[] selectIndoorTemperatureSensorInfo(Date date);

    public TemperatureSensorInfo selectCurrentOutdoorTemperatureSensorInfo();
}
