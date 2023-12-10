package com.shopping.userDataModule1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usertable")
public class UserModel {
    @Id
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private String mobileNo;
    private String UserRole;

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
//    private String address;
//    private String state;
//    private String city;
//    private String country;
//    private Integer pincode;
//    private String mobileCountryCode;

    public UserModel(UserModel user) {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public UserModel() {
        super();
    }

//    public UserModel(Integer userId, String userName, String password, String email, String mobileNo) {
//        this.userId = userId;
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.mobileNo = mobileNo;
////        this.address = address;
////        this.state = state;
////        this.city = city;
////        this.country = country;
////        this.pincode = pincode;
////        this.mobileCountryCode = mobileCountryCode;
//    }
    public UserModel(Integer userId, String userName, String password, String email, String mobileNo, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.UserRole = userRole;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + userId +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
