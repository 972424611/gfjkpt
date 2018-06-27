package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.Load;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Twilight
 */
public interface LoadMapper {

    List<Load> selectLoadChart(@Param("local") String local, @Param("date") String date);

    Load selectLoadNewest(@Param("local") String local);

    List<Load> selectLoadByLocalAndDate(@Param("local") String local, @Param("date") String date);
}
