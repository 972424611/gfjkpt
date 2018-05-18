package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.vo.InverterChartVo;
import com.cslg.gfjkpt.vo.InverterIconVo;
import com.cslg.gfjkpt.vo.InverterVo;
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

    private final InverterMapper inverterMapper;

    @Autowired
    public InverterServiceImpl(InverterMapper inverterMapper) {
        this.inverterMapper = inverterMapper;
    }

    @Override
    public InverterVo getInverterData(String inverterName, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        //TODO 这里区别下用户
        InverterVo inverterVo = new InverterVo();
        Integer sum = inverterMapper.selectInverterTotal(inverterName);
        List<Inverter> inverterList =  inverterMapper.selectInverter(inverterName, pageQuery);
        List<InverterVo.Inverter> resultList = new ArrayList<>();
        for(Inverter i : inverterList) {
            InverterVo.Inverter inverter = new InverterVo().new Inverter();
            BeanUtils.copyProperties(i, inverter);
            resultList.add(inverter);
        }
        inverterVo.setInverterList(resultList);
        inverterVo.setSum(sum);
        return inverterVo;
    }

    private void addQuarter(List<InverterChartVo> result, List<InverterChartVo> list, String quarter) {
        if(list.size() == 0) {
            return;
        }
        InverterChartVo inverterChartVo = new InverterChartVo();
        double dailyOutputTotal = 0;
        double tansTemp1Total = 0;
        double tansTemp2Total = 0;
        for(InverterChartVo i : list) {
            dailyOutputTotal += i.getDailyOutput();
            tansTemp1Total += i.getTansTemp1();
            tansTemp2Total += i.getTansTemp2();
        }
        inverterChartVo.setDailyOutput(dailyOutputTotal / list.size());
        inverterChartVo.setTansTemp1(tansTemp1Total / list.size());
        inverterChartVo.setTansTemp2(tansTemp2Total / list.size());
        inverterChartVo.setTimes(quarter);
        result.add(inverterChartVo);
    }

    private List<InverterChartVo> dealQuarter(List<InverterChartVo> list) {
        List<InverterChartVo> first = new ArrayList<>();
        List<InverterChartVo> second = new ArrayList<>();
        List<InverterChartVo> third = new ArrayList<>();
        List<InverterChartVo> fourth = new ArrayList<>();
        for(InverterChartVo inverterChartVo : list) {
            System.out.println(inverterChartVo.getTimes());
            String end = inverterChartVo.getTimes().split("-")[1];
            if("01".equals(end) || "02".equals(end) || "03".equals(end)) {
                first.add(inverterChartVo);
            } else if("04".equals(end) || "05".equals(end) || "06".equals(end)) {
                second.add(inverterChartVo);
            } else if("07".equals(end) || "08".equals(end) || "09".equals(end)) {
                third.add(inverterChartVo);
            } else if("10".equals(end) || "11".equals(end) || "12".equals(end)) {
                fourth.add(inverterChartVo);
            }
        }
        list.clear();
        addQuarter(list, first, "第一季度");
        addQuarter(list, second, "第二季度");
        addQuarter(list, third, "第三季度");
        addQuarter(list, fourth, "第四季度");
        return list;
    }

    private List<InverterChartVo> dealDate(int start, int end, InverterChartParam param, String s) {
        String str = "%" + param.getDate() + "%";
        //TODO; 这里应该区别下用户
        String inverterName = "inverter1";
        List<Inverter> list = inverterMapper.selectInverterChart(inverterName, str);
        List<InverterChartVo> inverterChartVoList = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            double dailyOutputTotal = 0;
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
                    dailyOutputTotal += inverter.getDailyOutput();
                    tansTemp1Total += inverter.getTansTemp1();
                    tansTemp2Total += inverter.getTansTemp2();
                    sum++;
                    flag = true;
                    iterator.remove();
                }
            }
            if(flag) {
                DecimalFormat df = new DecimalFormat("#.0");
                InverterChartVo inverterChartVo = new InverterChartVo();
                inverterChartVo.setDailyOutput(Double.valueOf(df.format(dailyOutputTotal / sum)));
                inverterChartVo.setTansTemp1(Double.valueOf(df.format(tansTemp1Total / sum)));
                inverterChartVo.setTansTemp2(Double.valueOf(df.format(tansTemp2Total / sum)));
                time = time.substring(time.length() - 2);
                inverterChartVo.setTimes(Integer.valueOf(time).toString() + s);
                inverterChartVoList.add(inverterChartVo);
            }
        }
        //如果是季度就特殊处理
        if("quarter".equals(param.getType())) {
            return dealQuarter(inverterChartVoList);
        }
        return inverterChartVoList;
    }

    @Override
    public List<InverterChartVo> getInverterChart(InverterChartParam inverterChartParam) {
        BeanValidator.check(inverterChartParam);
        if("day".equals(inverterChartParam.getType())) {
            return dealDate(6, 19, inverterChartParam, ":00");
        } else if("month".equals(inverterChartParam.getType())) {
            return dealDate(1, 30, inverterChartParam, "日");
        } else if("quarter".equals(inverterChartParam.getType())) {
            return dealDate(1, 12, inverterChartParam, "");
        } else if("year".equals(inverterChartParam.getType())) {
            return dealDate(1, 12, inverterChartParam, "月");
        }
        return null;
    }

    @Override
    public List<String> getInverterNameList() {
        User user = RequestHolder.getCurrentUser();
        int userId = user.getId();
        return inverterMapper.selectInverterNameList(userId);
    }

    @Override
    public InverterIconVo getInverterIcon() {
        InverterIconVo inverterIconVo = new InverterIconVo();
        Inverter inverter = inverterMapper.selectInverterNewest();
        inverterIconVo.setCurrentOutput(inverter.getDailyOutput());
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        String s = decimalFormat.format(inverter.getTotalOutput() / 1000 * 0.997);
        inverterIconVo.setCo2Reduction(Double.parseDouble(s));
        inverterIconVo.setEquivalentTree(inverterIconVo.getCo2Reduction() / 1800);
        inverterIconVo.setTemperature(Math.max(inverter.getTansTemp1(), inverter.getTansTemp2()));
        inverterIconVo.setIrradiance(0);
        inverterIconVo.setTotalIncome(0);
        return inverterIconVo;
    }
}