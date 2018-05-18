package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.LoadChartParam;
import com.cslg.gfjkpt.vo.LoadChartVo;
import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.vo.LoadIconVo;

import java.util.List;
import java.util.TreeMap;

/**
 * 对负荷的操作
 * @author Twilight
 */
public interface LoadService {

    /**
     * 插入数据
     * @param load Load pojo
     */
    void saveLoadData(Load load);

    List<LoadChartVo> getLoadChart(LoadChartParam loadChartParam);

    LoadIconVo getLoadIcon();
}
