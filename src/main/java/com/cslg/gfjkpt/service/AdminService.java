package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;
import com.cslg.gfjkpt.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {

    List<PowerStationInfo> getList(PageQuery pageQuery);

    long getUserTotal();

    List<User> getUserList(PageQuery pageQuery);

    void visitUser(Integer id, HttpServletResponse response);
}
