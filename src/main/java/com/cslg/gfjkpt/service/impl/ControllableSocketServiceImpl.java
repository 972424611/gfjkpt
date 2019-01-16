package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.ControllableSocketParam;
import com.cslg.gfjkpt.mapper.ControllableSocketInfoMapper;
import com.cslg.gfjkpt.model.ControllableSocketInfo;
import com.cslg.gfjkpt.service.ControllableSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ControllableSocketServiceImpl implements ControllableSocketService {
    @Autowired
    ControllableSocketInfoMapper controllableSocketInfoMapper;
    @Override
    public ControllableSocketInfo[] selectControllableSocketInfo(Date time) {
        ControllableSocketInfo[] controllableSocketInfos=controllableSocketInfoMapper.selectControllableSocketInfo(time);
        return controllableSocketInfos;
    }

    @Override
    public List<ControllableSocketParam> getControllableSocketParam(ControllableSocketInfo[] controllableSocketInfos) {
        double[] voltage=new double[48];
        double[] current=new double[48];
        double[] electricPower=new double[48];

        int[] counts=new int[48];
        List<ControllableSocketParam> controllableSocketParamList=new ArrayList<>();
        Timestamp timestamp=null;

        for (int i=0;i<controllableSocketInfos.length;i++){
            timestamp=controllableSocketInfos[i].getTime();
            int num=timestamp.getHours()*2+timestamp.getMinutes()/30;
            voltage[num]+=controllableSocketInfos[i].getVoltage();
            current[num]+=controllableSocketInfos[i].getCurrent();
            electricPower[num]+=controllableSocketInfos[i].getElectricPower();
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
                controllableSocketParamList.add(new ControllableSocketParam(
                        i+1,
                        voltage[i]/counts[i],
                        current[i]/counts[i],
                        electricPower[i]/counts[i],
                        time
                ));
            }
        }

        if (controllableSocketParamList.size()==0){
            return null;
        }

        return controllableSocketParamList;
    }
}
