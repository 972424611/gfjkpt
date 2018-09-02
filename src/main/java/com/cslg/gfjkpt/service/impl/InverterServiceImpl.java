package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.inverter.PredictParam;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.vo.inverter.ChartVo;
import com.cslg.gfjkpt.vo.inverter.IconVo;
import com.cslg.gfjkpt.vo.inverter.InverterVo;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.vo.inverter.PredictVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Twilight
 */
@Service
public class InverterServiceImpl implements InverterService {

    private final InverterMapper inverterMapper;

    private static ConcurrentMap<String, String> fieldMap = new ConcurrentHashMap<>();

    static {
        fieldMap.put("日发电量", "dailyOutput");
        fieldMap.put("总发电量", "totalOutput");
        fieldMap.put("总有功功率", "totalActivePower");
    }

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

    private void addQuarter(List<ChartVo> result, List<ChartVo> list, String quarter) {
        if(list.size() == 0) {
            return;
        }
        ChartVo chartVo = new ChartVo();
        double field = 0;
        double tansTemp1Total = 0;
        double tansTemp2Total = 0;
        for(ChartVo i : list) {
            field += i.getField();
            tansTemp1Total += i.getTansTemp1();
            tansTemp2Total += i.getTansTemp2();
        }
        chartVo.setField(field / list.size());
        chartVo.setTansTemp1(tansTemp1Total / list.size());
        chartVo.setTansTemp2(tansTemp2Total / list.size());
        chartVo.setTimes(quarter);
        result.add(chartVo);
    }

    private List<ChartVo> dealQuarter(List<ChartVo> list) {
        List<ChartVo> first = new ArrayList<>();
        List<ChartVo> second = new ArrayList<>();
        List<ChartVo> third = new ArrayList<>();
        List<ChartVo> fourth = new ArrayList<>();
        for(ChartVo chartVo : list) {
            System.out.println(chartVo.getTimes());
            String end = chartVo.getTimes().split("-")[1];
            if("01".equals(end) || "02".equals(end) || "03".equals(end)) {
                first.add(chartVo);
            } else if("04".equals(end) || "05".equals(end) || "06".equals(end)) {
                second.add(chartVo);
            } else if("07".equals(end) || "08".equals(end) || "09".equals(end)) {
                third.add(chartVo);
            } else if("10".equals(end) || "11".equals(end) || "12".equals(end)) {
                fourth.add(chartVo);
            }
        }
        list.clear();
        addQuarter(list, first, "第一季度");
        addQuarter(list, second, "第二季度");
        addQuarter(list, third, "第三季度");
        addQuarter(list, fourth, "第四季度");
        return list;
    }

    private List<ChartVo> dealDate(int start, int end, ChartParam param, String s) {
        String str = "%" + param.getDate() + "%";
        //TODO; 这里应该区别下用户
        String inverterName = "inverter1";
        List<Inverter> list = inverterMapper.selectInverterChart(inverterName, str);
        List<ChartVo> chartVoList = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            double field = 0;
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
                    try {
                        str = fieldMap.get(param.getField());
                        String methodName = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                        Class<?> c = Inverter.class;
                        Method method = c.getMethod(methodName);
                        Object o = method.invoke(inverter);
                        field += (double) o;
                        tansTemp1Total += inverter.getTansTemp1();
                        tansTemp2Total += inverter.getTansTemp2();
                        sum++;
                        flag = true;
                        iterator.remove();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(flag) {
                DecimalFormat df = new DecimalFormat("#.0");
                ChartVo chartVo = new ChartVo();
                chartVo.setField(Double.valueOf(df.format(field / sum)));
                chartVo.setTansTemp1(Double.valueOf(df.format(tansTemp1Total / sum)));
                chartVo.setTansTemp2(Double.valueOf(df.format(tansTemp2Total / sum)));
                time = time.substring(time.length() - 2);
                chartVo.setTimes(Integer.valueOf(time).toString() + s);
                chartVoList.add(chartVo);
            } else {
                ChartVo chartVo = new ChartVo(0, 0, 0);
                time = time.substring(time.length() - 2);
                chartVo.setTimes(Integer.valueOf(time).toString() + s);
                chartVoList.add(chartVo);
            }
        }
        //如果是季度就特殊处理
        if("quarter".equals(param.getType())) {
            return dealQuarter(chartVoList);
        }
        return chartVoList;
    }

    @Override
    public List<ChartVo> getInverterChart(ChartParam chartParam) {
        //BeanValidator.check(chartParam);
        if("day".equals(chartParam.getType())) {
            return dealDate(1, 24, chartParam, ":00");
        } else if("month".equals(chartParam.getType())) {
            return dealDate(1, 30, chartParam, "日");
        } else if("quarter".equals(chartParam.getType())) {
            return dealDate(1, 12, chartParam, "");
        } else if("year".equals(chartParam.getType())) {
            return dealDate(1, 12, chartParam, "月");
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
    public IconVo getInverterIcon() {
        IconVo iconVo = new IconVo();
        Inverter inverter = inverterMapper.selectInverterNewest();
        iconVo.setCurrentOutput(inverter.getTotalActivePower());
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        String s = decimalFormat.format(inverter.getTotalOutput() / 1000 * 0.997);
        iconVo.setCo2Reduction(Double.parseDouble(s));
        iconVo.setEquivalentTree(iconVo.getCo2Reduction() / 1800);
        iconVo.setTemperature(Math.max(inverter.getTansTemp1(), inverter.getTansTemp2()));
        iconVo.setIrradiance(0);
        s = decimalFormat.format(inverter.getTotalOutput() * 0.588 * 0.001);
        iconVo.setTotalIncome(Double.parseDouble(s));
        return iconVo;
    }

    private PredictVo.Vo getVo(ChartVo chartVo) {
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        PredictVo.Vo vo = new PredictVo().new Vo();
        vo.setCurrentOutput(chartVo.getField());
        String s = decimalFormat.format(chartVo.getField() * 0.001 * 0.588);
        vo.setCurrentIncome(Double.parseDouble(s));

        s = decimalFormat.format(chartVo.getField() * 1.032);
        vo.setPredictOutput(Double.parseDouble(s));
        s = decimalFormat.format(vo.getPredictOutput() * 0.001 * 0.588);
        vo.setPredictIncome(Double.parseDouble(s));
        vo.setTimes(chartVo.getTimes());
        return vo;
    }

    @Override
    public PredictVo getInverterPredict(PredictParam predictParam) {
        BeanValidator.check(predictParam);
        PredictVo predictVo = new PredictVo();
        List<PredictVo.Vo> voDayList = new ArrayList<>();
        List<PredictVo.Vo> voMonthList = new ArrayList<>();

        ChartParam chartParam = new ChartParam();
        chartParam.setDate(predictParam.getDayDate());
        chartParam.setType("day");
        chartParam.setName("inverter1");
        chartParam.setField("日发电量");

        List<ChartVo> currentChartVoList = getInverterChart(chartParam);
        for(ChartVo currentChartVo : currentChartVoList) {
            voDayList.add(getVo(currentChartVo));
        }

        chartParam.setDate(predictParam.getMonthDate());
        chartParam.setType("month");

        currentChartVoList = getInverterChart(chartParam);
        for(ChartVo currentChartVo : currentChartVoList) {
            voMonthList.add(getVo(currentChartVo));
        }

        predictVo.setVoDayList(voDayList);
        predictVo.setVoMonthList(voMonthList);
        return predictVo;
    }

}