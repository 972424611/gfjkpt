package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.TemperatureSensorParam;
import com.cslg.gfjkpt.mapper.TemperatureSensorMapper;
import com.cslg.gfjkpt.model.TemperatureSensorInfo;
import com.cslg.gfjkpt.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TemperatureSensorServiceImpl implements TemperatureSensorService {
    @Autowired
    TemperatureSensorMapper temperatureSensorMapper;
    @Override
    public TemperatureSensorInfo[] getIndoorTemperatureSensorInfo(Date time) {
        TemperatureSensorInfo[] temperatureSensorInfos=temperatureSensorMapper.selectIndoorTemperatureSensorInfo(time);
        return temperatureSensorInfos;
    }

    @Override
    public List<TemperatureSensorParam> getIndoorTemperatureSensorChart(TemperatureSensorInfo[] temperatureSensorInfos) {
        List<TemperatureSensorParam> temperatureSensorParamList=new ArrayList<>();
        double[] temperature=new double[48];
        double[] humidity=new double[48];
        int[] counts=new int[48];

        Timestamp timestamp=null;

        for (int i=0;i<temperatureSensorInfos.length;i++){
            timestamp=temperatureSensorInfos[i].getTime();
            int num=timestamp.getHours()*2+timestamp.getMinutes()/30;
            temperature[num]+=temperatureSensorInfos[i].getTemperature();
            humidity[num]+=temperatureSensorInfos[i].getHumidity();
            counts[num]++;
        }
        String time="";
        for (int i=0;i<counts.length;i++){
            if (counts[i]!=0){
                time="";
                int h=i/2;
                if (h<=9){
                    time+=("0"+h);
                }else {
                    time+=h;
                }
                time+=":";
                if (i%2==0){
                    time+="00";
                }else {
                    time+="30";
                }
                temperatureSensorParamList.add(new TemperatureSensorParam(i+1,temperature[i]/counts[i],humidity[i]/counts[i],time));
            }
        }
        if (temperatureSensorParamList.size()==0){
            return null;
        }
        return temperatureSensorParamList;
    }

    @Override
    public TemperatureSensorInfo getCurrentOutdoorTemperatureSensorInfo() {
        TemperatureSensorInfo temperatureSensorInfo=temperatureSensorMapper.selectCurrentOutdoorTemperatureSensorInfo();
        return temperatureSensorInfo;
    }
}
