package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.UserParam;
import com.cslg.gfjkpt.vo.UserVo;

import java.util.List;

public interface AdminService {

    void addUser(UserParam userParam);

    List<UserVo> getUsers();

    void deleteUserById(int id);
}
