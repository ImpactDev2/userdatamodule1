package com.shopping.userDataModule1.service;

import com.shopping.userDataModule1.dao.UserDao;
import com.shopping.userDataModule1.exception.InvalidTokenException;
import com.shopping.userDataModule1.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService, UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserModel addUser(UserModel user) {
        return this.userDao.save(user);
    }

    @Override
    public UserModel findByUserName(String UserName) {
        return this.userDao.findUserByUserName(UserName);
    }

    @Override
    public UserModel validateUser(String username, String password) {
        return this.userDao.validateLogin(username,password);
    }

    @Override
    public List getUserIds() {
        return this.userDao.getUserIds();
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }

    @Override
    public Integer getUserCount() {
        return this.userDao.getUserCount();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserModel user = this.userDao.findUserByUserName(username);
            if(username.equals(user.getUsername())){
                return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
            }else {
                throw new UsernameNotFoundException("user not found");
            }
        }catch(Exception e){

        }
        return null;
    }
}
