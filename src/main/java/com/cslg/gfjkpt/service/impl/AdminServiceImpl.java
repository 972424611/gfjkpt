package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.PageQuery;
import com.cslg.gfjkpt.beans.PowerStationInfo;
import com.cslg.gfjkpt.common.BeanValidator;
import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.mapper.AdminMapper;
import com.cslg.gfjkpt.mapper.InverterMapper;
import com.cslg.gfjkpt.mapper.LoadMapper;
import com.cslg.gfjkpt.mapper.UserMapper;
import com.cslg.gfjkpt.model.Inverter;
import com.cslg.gfjkpt.model.Load;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.service.AdminService;
import com.cslg.gfjkpt.service.LoadService;
import com.cslg.gfjkpt.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private InverterMapper inverterMapper;

    @Autowired
    private UserMapper userMapper;

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
                Inverter inverter = inverterMapper.selectInverterByName(inverterName);
                dailyOutput = dailyOutput + inverter.getDailyOutput();
                totalOutput = totalOutput + inverter.getTotalOutput();
                date = inverter.getTimes();
            }
            PowerStationInfo powerStationInfo = new PowerStationInfo();
            powerStationInfo.setUserId(user.getId());
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

    @Override
    public List<User> getUserList(PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        return adminMapper.selectUserPage(pageQuery);
    }

    @Override
    public void visitUser(Integer id, HttpServletResponse response) {
        User user = userMapper.selectUserById(id);
        HttpServletRequest request = RequestHolder.getCurrentRequest();
        CookieSessionManage.setSession(request, user);
        CookieSessionManage.setCookie(response, "admin");
    }
}
