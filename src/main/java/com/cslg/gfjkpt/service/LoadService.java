package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.load.ChartParam;
import com.cslg.gfjkpt.beans.load.ContrastChartParam;
import com.cslg.gfjkpt.beans.load.ContrastParam;
import com.cslg.gfjkpt.vo.load.*;

import java.util.List;
import java.util.Set;

/**
 * 对负荷的操作
 * @author Twilight
 */
public interface LoadService {

    List<ChartVo> getLoadChart(ChartParam chartParam);

    IconVo getLoadIcon(String local);

    Set<ContrastChartVo> getContrastChart(ContrastChartParam contrastChartParam);

    List<PieChartVo> getPieChart();

    List<ContrastVo> getContrast(ContrastParam contrastParam);
}
