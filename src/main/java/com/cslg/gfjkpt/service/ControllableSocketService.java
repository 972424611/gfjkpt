package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.ControllableSocketParam;
import com.cslg.gfjkpt.beans.bp.Data;
import com.cslg.gfjkpt.model.ControllableSocketInfo;

import java.util.Date;
import java.util.List;

public interface ControllableSocketService {
    public ControllableSocketInfo[] selectControllableSocketInfo(Date time);

    public List<ControllableSocketParam> getControllableSocketParam(ControllableSocketInfo[] controllableSocketInfos);
}
