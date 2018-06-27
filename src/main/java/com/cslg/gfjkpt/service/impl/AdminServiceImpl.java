package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.mapper.AdminMapper;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.service.AdminService;
import com.cslg.gfjkpt.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void addUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        adminMapper.insertUser(user);
    }

    @Override
    public List<UserVo> getUsers() {
        List<User> userList = adminMapper.selectUsers();
        List<UserVo> userVoList = new ArrayList<>();
        for(User user : userList) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        }
        return userVoList;
    }

    @Override
    public void deleteUserById(int id) {
        adminMapper.deleteUserById(id);
    }
}
