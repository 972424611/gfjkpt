package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.Load;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Twilight
 */
public interface LoadMapper {

    /**
     * 插入
     * @param load Load pojo
     */
    void insertLoad(Load load);

    /**
     * 通过日期, 获取负荷能耗, 或月 年, 获取负荷能耗
     * @param loadName 负荷名称
     * @param date 日期 或月 年
     * @return List<Load>
     */
    List<Load> selectLoadByDate(@Param("loadName") String loadName, @Param("date") String date);

    /**
     * 通过月 年, 获取负荷能耗
     * @return List<Double>
     * @param loadName
     * @param date
     */
//    List<Double> selectLoadEnergy(String loadName, String date);
}
