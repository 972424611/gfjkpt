package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.load.ChartParam;
import com.cslg.gfjkpt.beans.load.ContrastChartParam;
import com.cslg.gfjkpt.beans.load.ContrastParam;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.mapper.LoadMapper;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.service.LoadService;
import com.cslg.gfjkpt.vo.load.*;
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
public class LoadServiceImpl implements LoadService {

    private final LoadMapper loadMapper;

    private final InverterService inverterService;

    @Autowired
    public LoadServiceImpl(LoadMapper loadMapper, InverterService inverterService) {
        this.loadMapper = loadMapper;
        this.inverterService = inverterService;
    }

    private List<ChartVo> dealDate(int start, int end, ChartParam param, String s) {
        String str = "%" + param.getDate() + "%";
        String local = "长沙理工大学" + param.getLocal();
        List<Load> loadList = loadMapper.selectLoadChart(local, str);
        List<ChartVo> chartVoList = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            double activePower = 0;
            double apparentPower = 0;
            double current = 0;
            double voltage = 0;
            int sum = 0;
            boolean flag = false;
            String time = "";
            if("day".equals(param.getType())) {
                time = i >= 10 ? param.getDate() + " " + i : param.getDate() + " 0" + i;
            } else if("month".equals(param.getType())
                    || "year".equals(param.getType())
                    || "quarter".equals(param.getType())) {
                time = i >= 10 ? param.getDate() + "-" + i : param.getDate() + "-0" + i;
            }
            Iterator iterator = loadList.iterator();
            while(iterator.hasNext()) {
                Load load = (Load) iterator.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = dateFormat.format(load.getTimes());
                if(strDate.contains(time)) {
                    if(load.getCurrent() != 0) {
                        activePower += load.getActivePower();
                        apparentPower += load.getApparentPower();
                        current += load.getCurrent();
                        voltage += load.getVoltage();
                        sum++;
                        flag = true;
                    }
                    iterator.remove();
                }
            }
            if(flag) {
                DecimalFormat df = new DecimalFormat("#.0");
                ChartVo chartVo = new ChartVo();
                chartVo.setActivePower(Double.valueOf(df.format(activePower / sum)));
                chartVo.setApparentPower(Double.valueOf(df.format(apparentPower / sum)));
                chartVo.setCurrent(Double.valueOf(df.format(current / sum)));
                chartVo.setVoltage(Double.valueOf(df.format(voltage / sum)));
                time = time.substring(time.length() - 2);
                chartVo.setTimes(Integer.valueOf(time).toString() + s);
                chartVoList.add(chartVo);
            }
        }
        return chartVoList;
    }

    @Override
    public List<ChartVo> getLoadChart(ChartParam chartParam) {
        BeanValidator.check(chartParam);
        if("day".equals(chartParam.getType())) {
            return dealDate(6, 19, chartParam, ":00");
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
    public IconVo getLoadIcon(String local) {
        IconVo iconVo = new IconVo();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String date = simpleDateFormat.format(new Date());
        date = "%" + date + "%";
        //TODO
        local = "长沙理工大学" + local;
        List<Load> loadList = loadMapper.selectLoadChart(local, date);
        double maxMouthPower = 0;
        for(Load load : loadList) {
            if(load.getActivePower() > maxMouthPower) {
                maxMouthPower = load.getActivePower();
            }
        }
        iconVo.setMaxMouthLoad(maxMouthPower);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        date = simpleDateFormat.format(new Date());
        date = "%" + date + "%";
        loadList =  loadMapper.selectLoadChart(local, date);
        double maxYearPower = 0;
        for(Load load : loadList) {
            if(load.getActivePower() > maxYearPower) {
                maxYearPower = load.getActivePower();
            }
        }
        iconVo.setMaxYearLoad(maxYearPower);
        //TODO
        Load load = loadMapper.selectLoadNewest(local);
        if(load != null) {
            iconVo.setCurrentLoad(load.getActivePower());
        } else {
            iconVo.setCurrentLoad(0);
        }
        iconVo.setPercentage(1.2);
        iconVo.setRatio(12.4);
        return iconVo;
    }

    @Override
    public Set<ContrastChartVo> getContrastChart(ContrastChartParam contrastChartParam) {
        BeanValidator.check(contrastChartParam);
        String[] locals = contrastChartParam.getLocals().split("-");
        ChartParam chartParam = new ChartParam();
        BeanUtils.copyProperties(contrastChartParam, chartParam);
        Set<ContrastChartVo> contrastChartVoSet = new HashSet<>();
        for(String local : locals) {
            chartParam.setLocal(local);
            List<ChartVo> chartVoList = getLoadChart(chartParam);
            ContrastChartVo contrastChartVo = new ContrastChartVo();
            contrastChartVo.setList(chartVoList);
            contrastChartVo.setLocal(local);
            contrastChartVoSet.add(contrastChartVo);
        }
        return contrastChartVoSet;
    }

    private List<PieChartVo.Vo> getVoList(String dateType) {
        String[] locals = {"长沙理工大学综合教学楼", "长沙理工大学工科一号楼", "长沙理工大学工科二号楼"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);
        String date = "%" + simpleDateFormat.format(new Date()) + "%";
        List<PieChartVo.Vo> voList = new ArrayList<>();
        for(String local : locals) {
            List<Load> loadList = loadMapper.selectLoadByLocalAndDate(local, date);
            if(loadList == null || loadList.size() == 0) {
                return null;
            }
            double totalActivePower = 0;
            for(Load load : loadList) {
                totalActivePower =+ load.getActivePower();
            }
            double averageActivePower = totalActivePower / loadList.size();
            Load endLoad = loadList.get(loadList.size() - 1);
            Load startLoad = loadList.get(0);
            long time = endLoad.getTimes().getTime() - startLoad.getTimes().getTime();
            time = time / 3600000;
            PieChartVo.Vo vo = new PieChartVo().new Vo();
            DecimalFormat decimalFormat = new DecimalFormat("######0.00");
            String s = decimalFormat.format(averageActivePower * time);
            vo.setConsume(Double.parseDouble(s));
            vo.setLocal(local);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<PieChartVo> getPieChart() {
        List<PieChartVo> pieChartVoList = new ArrayList<>();

        //日
        List<PieChartVo.Vo> voDayList = getVoList("yyyy-MM-dd");
        PieChartVo pieChartVo = new PieChartVo();
        pieChartVo.setVoList(voDayList);
        pieChartVo.setDateType("day");
        pieChartVoList.add(pieChartVo);
        //月
        List<PieChartVo.Vo> voMonthList = getVoList("yyyy-MM");
        pieChartVo = new PieChartVo();
        pieChartVo.setVoList(voMonthList);
        pieChartVo.setDateType("month");
        pieChartVoList.add(pieChartVo);

        return pieChartVoList;
    }

    @Override
    public List<ContrastVo> getContrast(ContrastParam contrastParam) {
        List<ContrastVo> contrastVoList = new ArrayList<>();
        com.cslg.gfjkpt.beans.load.ChartParam loadChartParam
                = new com.cslg.gfjkpt.beans.load.ChartParam();
        loadChartParam.setDate(contrastParam.getDate());
        loadChartParam.setType(contrastParam.getType());
        loadChartParam.setLocal("工科一号楼");
        List<ChartVo> loadChartVoList = getLoadChart(loadChartParam);

        com.cslg.gfjkpt.beans.inverter.ChartParam inverterChartParam
                = new com.cslg.gfjkpt.beans.inverter.ChartParam();
        inverterChartParam.setField("总有功功率");
        inverterChartParam.setDate(contrastParam.getDate());
        inverterChartParam.setType(contrastParam.getType());
        inverterChartParam.setName("inverter1");
        List<com.cslg.gfjkpt.vo.inverter.ChartVo> inverterChartVoList
                = inverterService.getInverterChart(inverterChartParam);

        for(com.cslg.gfjkpt.vo.inverter.ChartVo chartVo : inverterChartVoList) {
            ContrastVo contrastVo = new ContrastVo();
            contrastVo.setPowerGeneration(chartVo.getField());
            contrastVo.setTimes(chartVo.getTimes());
            Iterator iterator = loadChartVoList.iterator();
            while(iterator.hasNext()) {
                ChartVo loadChartVo = (ChartVo) iterator.next();
                if(loadChartVo.getTimes().equals(chartVo.getTimes())) {
                    contrastVo.setPowerConsumption(loadChartVo.getActivePower());
                    iterator.remove();
                    break;
                }
            }
            contrastVoList.add(contrastVo);
        }

        return contrastVoList;
    }

}
