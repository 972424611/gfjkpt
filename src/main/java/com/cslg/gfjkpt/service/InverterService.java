package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.InverterChartParam;
import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.dto.InverterChartDto;
import com.cslg.gfjkpt.dto.InverterDto;

import java.util.List;

/**
 * 对逆变器的操作
 * @author Twilight
 */
public interface InverterService {

    InverterDto getInverterData(String inverterName, PageQuery pageQuery);

    List<InverterChartDto> getInverterChart(InverterChartParam inverterChartParam);

    List<String> getInverterNameList();

}
