package com.shopping.userDataModule1.dao;

import com.shopping.userDataModule1.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<UserModel,Integer> {
    @Query(value="select * from usertable where user_name = :UserName",nativeQuery=true)
    public UserModel findUserByUserName(String UserName);

    @Query(value="select * from usertable where user_name=:username and password=:password",nativeQuery=true)
    public UserModel validateLogin(String username,String password);

    @Query(value = "select user_id from usertable",nativeQuery = true)
    public List getUserIds();

    @Query(value="select * from usertable where email =:email",nativeQuery = true)
    public UserModel findUserByEmail(String email);

    @Query(value = "select count(*) from usertable",nativeQuery = true)
    public Integer getUserCount();
}
