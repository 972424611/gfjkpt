package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.exception.InverterException;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.utils.VeDateUtils;
import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.service.InverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Twilight
 */
@Service
public class InverterServiceImpl implements InverterService {

    @Autowired
    private InverterMapper inverterMapper;

    @Override
    public void saveInverterData(String message) {
        String[] messages = message.split("\\$");
        Inverter inverter = new Inverter();
        for(int i = 0; i < messages.length; i++) {
            String[] msgs = messages[i].split(";");
            if(messages[i].startsWith("1")) {
                inverter.setaPhaseVoltage(Double.parseDouble(msgs[1]));
                inverter.setbPhaseVoltage(Double.parseDouble(msgs[2]));
                inverter.setcPhaseVoltage(Double.parseDouble(msgs[3]));
            } else if(messages[i].startsWith("2")) {
                inverter.setTotalActivePower(Double.parseDouble(msgs[4]));
            } else if(messages[i].startsWith("3")) {
                inverter.setaPhaseCurrent(Double.parseDouble(msgs[1]));
                inverter.setbPhaseCurrent(Double.parseDouble(msgs[2]));
                inverter.setcPhaseCurrent(Double.parseDouble(msgs[3]));
            } else if(messages[i].startsWith("6")) {
                inverter.setDailyOutput(Double.parseDouble(msgs[1]));
                inverter.setTotalOutput(Double.parseDouble(msgs[2]));
            }
        }
        inverter.setInverterName(RequestHolder.getCurrentUser().getUsername() + "_inverter_1");
        inverter.setTansTemp(0.0);
        inverter.setTimes(new Date());
        System.out.println(inverter.toString());
        inverterMapper.insertInverter(inverter);
    }

    @Override
    public List<Inverter> getInverterData(String inverterName, PageQuery pageQuery) {
        String username = RequestHolder.getCurrentUser().getUsername();
        inverterName = username + "_" + inverterName;
        BeanValidator.check(pageQuery);
        return inverterMapper.selectInverter(inverterName, pageQuery);
    }

    private int dealDateType(String dateType) {
        //默认为日, 就按照1小时区分 length = 60 / 15
        int length = 4;
        if("mouth".equals(dateType)) {
            length = 24 * 60 / 15;
        }else if("year".equals(dateType)) {
            length = 365 * 24 * 60 / 15;
        }
        return length;
    }

    @Override
    public TreeMap<String, Double> getInverterPower(String inverterName, String dateType, String detailDate) {
        int length = dealDateType(dateType);
        detailDate = "%" + detailDate + "%";
        String username = RequestHolder.getCurrentUser().getUsername();
        inverterName = username + "_" + inverterName;
        List<HashMap<String, Object>> list = inverterMapper.selectInverterPowerByDate(inverterName, detailDate);
        return dealInverterMap(list, length);
    }

    private TreeMap<String, Double> dealInverterMap(List<HashMap<String, Object>> inverterMap, int count) {
        HashMap<String, Object> testMap = new HashMap<>();
        TreeMap<String, Double> resultMap = new TreeMap<>();
        int i = 1;
        double powerSum = 0;
        for(HashMap<String, Object> map : inverterMap) {
            testMap.put(map.get("time").toString(), map.get("power"));
        }
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(testMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            //升序
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for(Map.Entry<String, Object> mapping : list) {
            powerSum = powerSum + (double) mapping.getValue();
            if(i % count == 0) {
                Date date = VeDateUtils.strToDateLong(mapping.getKey());
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
                double value = powerSum / (double) count;
                resultMap.put(key, value);
                powerSum = 0;
            }
            i++;
        }
        return resultMap;
    }

    @Override
    public long getInverterTotal(String inverterName) {
        String username = RequestHolder.getCurrentUser().getUsername();
        inverterName = username + "_" + inverterName;
        return inverterMapper.selectInverterTotal(inverterName);
    }

    @Override
    public List<String> getInverterNameList() {
        User user = RequestHolder.getCurrentUser();
        int userId = user.getId();
        return inverterMapper.selectInverterNameList(userId);
    }
}