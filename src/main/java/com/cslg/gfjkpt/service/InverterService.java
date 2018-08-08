package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.inverter.PredictParam;
import com.cslg.gfjkpt.vo.inverter.ChartVo;
import com.cslg.gfjkpt.vo.inverter.IconVo;
import com.cslg.gfjkpt.vo.inverter.InverterVo;
import com.cslg.gfjkpt.vo.inverter.PredictVo;

import java.util.List;

/**
 * 对逆变器的操作
 * @author Twilight
 */
public interface InverterService {

    InverterVo getInverterData(String inverterName, PageQuery pageQuery);

    List<ChartVo> getInverterChart(ChartParam chartParam);

    List<String> getInverterNameList();

    IconVo getInverterIcon();

    PredictVo getInverterPredict(PredictParam predictParam);
}
