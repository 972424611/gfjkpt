package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.ControllableSocketInfo;

import java.util.Date;

public interface ControllableSocketInfoMapper {
    public ControllableSocketInfo[] selectControllableSocketInfo(Date time);
}
