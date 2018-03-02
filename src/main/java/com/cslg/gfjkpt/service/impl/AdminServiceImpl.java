package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.mapper.AdminMapper;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.service.AdminService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private InverterService inverterService;

    @Override
    public List<PowerStationInfo> getList(PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        List<PowerStationInfo> resultList = Lists.newArrayList();
        List<User> userList = adminMapper.selectUserPage(pageQuery);
        for(User user : userList) {
            List<String> inverterNameList = adminMapper.selectInverterName(user.getId());
            double dailyOutput = 0;
            double totalOutput = 0;
            Date date = new Date();
            for(String inverterName : inverterNameList) {
                Inverter inverter = inverterService.getInverterData(inverterName);
                dailyOutput = dailyOutput + inverter.getDailyOutput();
                totalOutput = totalOutput + inverter.getTotalOutput();
                date = inverter.getTimes();
            }
            PowerStationInfo powerStationInfo = new PowerStationInfo();
            powerStationInfo.setDailyOutput(dailyOutput);
            powerStationInfo.setTotalOutput(totalOutput);
            powerStationInfo.setUpdateTimes(date);
            powerStationInfo.setTitle(user.getComment());
            resultList.add(powerStationInfo);
        }
        return resultList;
    }

    @Override
    public long getUserTotal() {
        return adminMapper.selectUserTotal();
    }
}
