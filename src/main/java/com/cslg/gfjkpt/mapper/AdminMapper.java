package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    List<User> selectAllUser();

    List<User> selectUserPage(@Param("pageQuery") PageQuery pageQuery);

    List<String> selectInverterName(@Param("userId") int userId);

    Long selectUserTotal();

    void insertRelation(@Param("userId") int userId, @Param("inverterName") String inverterName);


}
