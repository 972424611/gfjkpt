package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.model.Load;

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

    /**
     * 获取负荷能耗, 通过日期
     * @param name 负荷名称
     * @param dateType 年, 月, 日
     * @param detailDate 详细日期
     * @return TreeMap<String, Double>
     */
    TreeMap<String, Double> getLoadDataByDate(String name, String dateType, String detailDate);

}
