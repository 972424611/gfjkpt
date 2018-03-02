package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.mapper.LoadMapper;
import com.cslg.gfjkpt.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Twilight
 */
@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadMapper loadMapper;

    @Override
    public void saveLoadData(Load load) {
        loadMapper.insertLoad(load);
    }

    @Override
    public TreeMap<String, Double> getLoadDataByDate(String name, String dateType, String detailDate) {
        detailDate = "%" + detailDate + "%";
        List<Load> loadList = loadMapper.selectLoadByDate(name, detailDate);
        TreeMap<String, Double> resultMap = null;
        if(loadList != null && loadList.size() > 0) {
            resultMap = new TreeMap<>();
            for(int i = 0; i < loadList.size(); i++) {
                Load load = loadList.get(i);
                Date date = load.getTimes();
                String day = String.valueOf(date.getDate());
                String month = String.valueOf(date.getMonth() + 1);
                String hours = String.valueOf(date.getHours());
                if(Integer.parseInt(day) < 10) {
                    day = "0" + day;
                }
                if(Integer.parseInt(month) < 10) {
                    month = "0" + month;
                }
                if(Integer.parseInt(hours) < 10) {
                    hours = "0" + hours;
                }
                String key = month + "-" + day + " " + hours + "h";
                double value = load.getEnergyConsumption();
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }
}
