package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;

import java.util.List;

public interface AdminService {

    List<PowerStationInfo> getList(PageQuery pageQuery);

    long getUserTotal();


}
