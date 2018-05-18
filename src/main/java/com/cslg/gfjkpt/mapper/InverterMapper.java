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

    void insertInverter(Inverter inverter);

    Inverter selectInverterByName(@Param("inverterName") String inverterName);

    Integer selectInverterTotal(@Param("inverterName") String inverterName);

    List<Inverter> selectAllInverter(@Param("inverterName") String inverterName);

    List<Inverter> selectInverter(@Param("inverterName") String inverterName, @Param("pageQuery") PageQuery pageQuery);

    List<Inverter> selectInverterChart(@Param("inverterName") String inverterName, @Param("date") String date);

    List<String> selectInverterNameList(int userId);

    Inverter selectInverterNewest();
}