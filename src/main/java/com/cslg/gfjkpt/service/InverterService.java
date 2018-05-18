package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.vo.InverterChartVo;
import com.cslg.gfjkpt.vo.InverterIconVo;
import com.cslg.gfjkpt.vo.InverterVo;

import java.util.List;

/**
 * 对逆变器的操作
 * @author Twilight
 */
public interface InverterService {

    InverterVo getInverterData(String inverterName, PageQuery pageQuery);

    List<InverterChartVo> getInverterChart(InverterChartParam inverterChartParam);

    List<String> getInverterNameList();

    InverterIconVo getInverterIcon();

}
