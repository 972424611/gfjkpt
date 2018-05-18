package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.LoadChartParam;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.vo.LoadChartVo;
import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.mapper.LoadMapper;
import com.cslg.gfjkpt.service.LoadService;
import com.cslg.gfjkpt.vo.LoadIconVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Twilight
 */
@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadMapper loadMapper;

    @Autowired
    private InverterMapper inverterMapper;

    @Override
    public void saveLoadData(Load load) {
        String userName = RequestHolder.getCurrentUser().getUsername();
        String loadName = userName + "_" + load.getLoadName();
        load.setLoadName(loadName);
        loadMapper.insertLoad(load);
    }

    private List<LoadChartVo> dealDate(int start, int end, LoadChartParam param, String s) {
        String str = "%" + param.getDate() + "%";
        //TODO; 这里应该区别下用户
        String inverterName = "inverter1";
        List<Inverter> list = inverterMapper.selectInverterChart(inverterName, str);
        List<LoadChartVo> loadChartVoList = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            double totalActivePowerTotal = 0;
            double tansTemp1Total = 0;
            double tansTemp2Total = 0;
            int sum = 0;
            boolean flag = false;
            String time = "";
            if("day".equals(param.getType())) {
                time = i >= 10 ? param.getDate() + " " + i : param.getDate() + " 0" + i;
            } else if("month".equals(param.getType()) || "year".equals(param.getType()) || "quarter".equals(param.getType())) {
                time = i >= 10 ? param.getDate() + "-" + i : param.getDate() + "-0" + i;
            }
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                Inverter inverter = (Inverter) iterator.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = dateFormat.format(inverter.getTimes());
                if(strDate.contains(time)) {
                    totalActivePowerTotal += inverter.getTotalActivePower();
                    tansTemp1Total += inverter.getTansTemp1();
                    tansTemp2Total += inverter.getTansTemp2();
                    sum++;
                    flag = true;
                    iterator.remove();
                }
            }
            if(flag) {
                DecimalFormat df = new DecimalFormat("#.0");
                LoadChartVo loadChartVo = new LoadChartVo();
                loadChartVo.setTotalActivePower(Double.valueOf(df.format(totalActivePowerTotal / sum)));
                loadChartVo.setTansTemp1(Double.valueOf(df.format(tansTemp1Total / sum)));
                loadChartVo.setTansTemp2(Double.valueOf(df.format(tansTemp2Total / sum)));
                time = time.substring(time.length() - 2);
                loadChartVo.setTimes(Integer.valueOf(time).toString() + s);
                loadChartVoList.add(loadChartVo);
            }
        }
        return loadChartVoList;
    }

    @Override
    public List<LoadChartVo> getLoadChart(LoadChartParam loadChartParam) {
        BeanValidator.check(loadChartParam);
        if("day".equals(loadChartParam.getType())) {
            return dealDate(6, 19, loadChartParam, ":00");
        } else if("month".equals(loadChartParam.getType())) {
            return dealDate(1, 30, loadChartParam, "日");
        } else if("quarter".equals(loadChartParam.getType())) {
            return dealDate(1, 12, loadChartParam, "");
        } else if("year".equals(loadChartParam.getType())) {
            return dealDate(1, 12, loadChartParam, "月");
        }
        return null;
    }

    @Override
    public LoadIconVo getLoadIcon() {
        LoadIconVo loadIconVo = new LoadIconVo();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String date = simpleDateFormat.format(new Date());
        date = "%" + date + "%";
        //TODO
        String inverterName = "inverter1";
        List<Inverter> inverterList = inverterMapper.selectInverterChart(inverterName, date);
        double maxMouthPower = 0;
        for(Inverter inverter : inverterList) {
            if(inverter.getTotalActivePower() > maxMouthPower) {
                maxMouthPower = inverter.getTotalActivePower();
            }
        }
        loadIconVo.setMaxMouthLoad(maxMouthPower);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        date = simpleDateFormat.format(new Date());
        date = "%" + date + "%";
        inverterList = inverterMapper.selectInverterChart(inverterName, date);
        double maxYearPower = 0;
        for(Inverter inverter : inverterList) {
            if(inverter.getTotalActivePower() > maxYearPower) {
                maxYearPower = inverter.getTotalActivePower();
            }
        }
        loadIconVo.setMaxYearLoad(maxYearPower);
        Inverter inverter = inverterMapper.selectInverterNewest();
        loadIconVo.setCurrentLoad(inverter.getTotalActivePower());
        loadIconVo.setPercentage(1.2);
        loadIconVo.setRatio(12.4);
        return loadIconVo;
    }
}
