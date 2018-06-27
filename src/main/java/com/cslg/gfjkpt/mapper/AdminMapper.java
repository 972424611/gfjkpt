package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.User;

import java.util.List;

public interface AdminMapper {

    void insertUser(User user);

    List<User> selectUsers();

    void deleteUserById(int id);
}
