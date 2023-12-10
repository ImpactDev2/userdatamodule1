package com.shopping.userDataModule1.service;

import com.shopping.userDataModule1.model.UserModel;

import java.util.List;

public interface UserDetailService {

    public UserModel addUser(UserModel user);
    public UserModel findByUserName(String UserName);
    public UserModel validateUser(String username,String password);
    public List getUserIds();
    public UserModel findUserByEmail(String email);
    public Integer getUserCount();
}
