package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.model.Inverter;

import java.util.List;
import java.util.TreeMap;

/**
 * 对逆变器的操作
 * @author Twilight
 */
public interface InverterService {

    /**
     * 插入数据
     */
    void saveInverterData(String message);

    /**
     * 获取数据
     * @param inverterName 逆变器名
     * @return Inverter pojo
     */
    List<Inverter> getInverterData(String inverterName, PageQuery pageQuery);

    /**
     * 获取逆变器功率通过日期
     * @param inverterName 逆变器名称
     * @param dateType 一般是year, mouth, day
     * @param detailDate 详细日期
     * @return TreeMap<String, Double>
     */
    TreeMap<String, Double> getInverterPower(String inverterName, String dateType, String detailDate);

    /**
     * 获取该逆变器总记录数
     * @param inverterName 逆变器名称
     * @return long
     */
    long getInverterTotal(String inverterName);

    /**
     * 获取最新的数据
     * @param inverterName
     * @return
     */
    Inverter getInverterData(String inverterName);
}
