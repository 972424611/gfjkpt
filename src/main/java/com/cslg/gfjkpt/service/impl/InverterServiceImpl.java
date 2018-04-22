package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.dto.InverterChartDto;
import com.cslg.gfjkpt.dto.InverterDto;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.service.InverterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Twilight
 */
@Service
public class InverterServiceImpl implements InverterService {

    @Autowired
    private InverterMapper inverterMapper;

    @Override
    public InverterDto getInverterData(String inverterName, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        //TODO 这里区别下用户
        InverterDto inverterDto = new InverterDto();
        Integer sum = inverterMapper.selectInverterTotal(inverterName);
        List<Inverter> inverterList =  inverterMapper.selectInverter(inverterName, pageQuery);
        List<InverterDto.Inverter> resultList = new ArrayList<>();
        for(Inverter i : inverterList) {
            InverterDto.Inverter inverter = new InverterDto().new Inverter();
            BeanUtils.copyProperties(i, inverter);
            resultList.add(inverter);
        }
        inverterDto.setInverterList(resultList);
        inverterDto.setSum(sum);
        return inverterDto;
    }

    private void addQuarter(List<InverterChartDto> result, List<InverterChartDto> list, String quarter) {
        if(list.size() == 0) {
            return;
        }
        InverterChartDto inverterChartDto = new InverterChartDto();
        double powerTotal = 0;
        double tansTemp1Total = 0;
        double tansTemp2Total = 0;
        for(InverterChartDto i : list) {
            powerTotal += i.getTotalActivePower();
            tansTemp1Total += i.getTansTemp1();
            tansTemp2Total += i.getTansTemp2();
        }
        inverterChartDto.setTotalActivePower(powerTotal / list.size());
        inverterChartDto.setTansTemp1(tansTemp1Total / list.size());
        inverterChartDto.setTansTemp2(tansTemp2Total / list.size());
        inverterChartDto.setTimes(quarter);
        result.add(inverterChartDto);
    }

    private List<InverterChartDto> dealQuarter(List<InverterChartDto> list) {
        List<InverterChartDto> first = new ArrayList<>();
        List<InverterChartDto> second = new ArrayList<>();
        List<InverterChartDto> third = new ArrayList<>();
        List<InverterChartDto> fourth = new ArrayList<>();
        for(InverterChartDto inverterChartDto : list) {
            System.out.println(inverterChartDto.getTimes());
            String end = inverterChartDto.getTimes().split("-")[1];
            if("01".equals(end) || "02".equals(end) || "03".equals(end)) {
                first.add(inverterChartDto);
            } else if("04".equals(end) || "05".equals(end) || "06".equals(end)) {
                second.add(inverterChartDto);
            } else if("07".equals(end) || "08".equals(end) || "09".equals(end)) {
                third.add(inverterChartDto);
            } else if("10".equals(end) || "11".equals(end) || "12".equals(end)) {
                fourth.add(inverterChartDto);
            }
        }
        list.clear();
        addQuarter(list, first, "第一季度");
        addQuarter(list, second, "第二季度");
        addQuarter(list, third, "第三季度");
        addQuarter(list, fourth, "第四季度");
        return list;
    }

    private List<InverterChartDto> dealDate(int start, int end, InverterChartParam param) {
        String str = "%" + param.getDate() + "%";
        //TODO; 这里应该区别下用户
        String inverterName = "inverter1";
        List<Inverter> list = inverterMapper.selectInverterChart(inverterName, str);
        List<InverterChartDto> inverterChartDtoList = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            double powerTotal = 0;
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
                    powerTotal += inverter.getTotalActivePower();
                    tansTemp1Total += inverter.getTansTemp1();
                    tansTemp2Total += inverter.getTansTemp2();
                    sum++;
                    flag = true;
                    iterator.remove();
                }
            }
            if(flag) {
                DecimalFormat df = new DecimalFormat("#.0");
                InverterChartDto inverterChartDto = new InverterChartDto();
                inverterChartDto.setTotalActivePower(Double.valueOf(df.format(powerTotal / sum)));
                inverterChartDto.setTansTemp1(Double.valueOf(df.format(tansTemp1Total / sum)));
                inverterChartDto.setTansTemp2(Double.valueOf(df.format(tansTemp2Total / sum)));
                inverterChartDto.setTimes(time);
                inverterChartDtoList.add(inverterChartDto);
            }
        }
        //如果是季度就特殊处理
        if("quarter".equals(param.getType())) {
            return dealQuarter(inverterChartDtoList);
        }
        return inverterChartDtoList;
    }

    @Override
    public List<InverterChartDto> getInverterChart(InverterChartParam inverterChartParam) {
        BeanValidator.check(inverterChartParam);
        if("day".equals(inverterChartParam.getType())) {
            return dealDate(6, 18, inverterChartParam);
        } else if("month".equals(inverterChartParam.getType())) {
            return dealDate(1, 30, inverterChartParam);
        } else if("quarter".equals(inverterChartParam.getType())) {
            return dealDate(1, 12, inverterChartParam);
        } else if("year".equals(inverterChartParam.getType())) {
            return dealDate(1, 12, inverterChartParam);
        }
        return null;
    }

    @Override
    public List<String> getInverterNameList() {
        User user = RequestHolder.getCurrentUser();
        int userId = user.getId();
        return inverterMapper.selectInverterNameList(userId);
    }
}