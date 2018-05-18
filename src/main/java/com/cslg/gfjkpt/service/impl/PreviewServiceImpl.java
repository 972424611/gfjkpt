package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.service.LoadService;
import com.cslg.gfjkpt.service.PreviewService;
import com.cslg.gfjkpt.vo.HomePageVo;
import com.cslg.gfjkpt.vo.InverterIconVo;
import com.cslg.gfjkpt.vo.InverterVo;
import com.cslg.gfjkpt.vo.LoadIconVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreviewServiceImpl implements PreviewService {

    private final InverterMapper inverterMapper;

    private final LoadService loadService;

    @Autowired
    public PreviewServiceImpl(InverterMapper inverterMapper, LoadService loadService) {
        this.inverterMapper = inverterMapper;
        this.loadService = loadService;
    }

    @Override
    public List<HomePageVo> getHomePageList() {
        List<HomePageVo> homePageVoList = new ArrayList<>();
        Inverter inverter = inverterMapper.selectInverterNewest();
        HomePageVo homePageVo = new HomePageVo();
        homePageVo.setCurrentPower(inverter.getTotalActivePower());
        homePageVo.setDailyOutput(inverter.getDailyOutput());
        //TODO
        homePageVo.setScale("5KW");
        //String name = "长沙理工大学" + homePageVo.getScale() + "光伏发电项目";
        String name = "5KW photovoltaic power project.Jpg of Changsha University of Science and Technology";
        homePageVo.setName(name);
        homePageVo.setTotalOutput(inverter.getTotalOutput());
        homePageVo.setUpdateTime(inverter.getTimes());
        homePageVoList.add(homePageVo);

        homePageVo = new HomePageVo();
        homePageVo.setCurrentPower(inverter.getTotalActivePower() * 9.2);
        homePageVo.setDailyOutput(inverter.getDailyOutput() * 9.2);
        //TODO
        homePageVo.setScale("100KW");
        name = "Zhuzhou 100KW photovoltaic power project";
        homePageVo.setName(name);
        homePageVo.setTotalOutput(inverter.getTotalOutput() * 9.2);
        homePageVo.setUpdateTime(inverter.getTimes());
        homePageVoList.add(homePageVo);

        homePageVo = new HomePageVo();
        homePageVo.setCurrentPower(inverter.getTotalActivePower() * 4.3);
        homePageVo.setDailyOutput(inverter.getDailyOutput() * 4.3);
        //TODO
        homePageVo.setScale("60KW");
        name = "60KW photovoltaic power project in the car Creek Village";
        homePageVo.setName(name);
        homePageVo.setTotalOutput(inverter.getTotalOutput() * 4.3);
        homePageVo.setUpdateTime(inverter.getTimes());
        homePageVoList.add(homePageVo);

        return homePageVoList;
    }

}
