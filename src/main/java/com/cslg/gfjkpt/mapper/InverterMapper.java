package com.cslg.gfjkpt.mapper;


import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.model.Inverter;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author Twilight
 */
public interface InverterMapper {

    /**
     * 添加数据inverter
     * @param inverter inverter
     */
    void insertInverter(Inverter inverter);

    /**
     * 获取最新的数据
     * @param inverterName InverterName
     * @return Inverter
     */
    Inverter selectInverterByName(@Param("inverterName") String inverterName);

    /**
     * 获取总数据数
     * @param inverterName 逆变器的名字
     * @return Long
     */
    Long selectInverterTotal(@Param("inverterName") String inverterName);

    /**
     * 获取全部数据
     * @param inverterName 逆变器的名字
     * @return List<Inverter>
     */
    List<Inverter> selectAllInverter(@Param("inverterName") String inverterName);

    /**
     * 分页获取
     * @param inverterName 逆变器名称
     * @return List<Inverter>
     */
    List<Inverter> selectInverter(@Param("inverterName") String inverterName, @Param("pageQuery") PageQuery pageQuery);

    /**
     * 获取逆变器功率 通过日期
     * @param inverterName 逆变器名称
     * @param date 格式类似 2017-01-15
     * @return List<HashMap<String, Object>>
     */
    List<HashMap<String, Object>> selectInverterPowerByDate(@Param("inverterName") String inverterName, @Param("date") String date);

    /**
     * 获取用户逆变器
     * @param userId 用户id
     * @return List<String>
     */
    List<String> selectInverterNameList(int userId);
}