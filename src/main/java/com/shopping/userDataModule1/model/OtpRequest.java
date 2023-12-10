package com.shopping.userDataModule1.model;

public class OtpRequest {
    private String email;
    private String flag;
    private String otp;

    public OtpRequest(String email, String flag, String otp) {
        this.email = email;
        this.flag = flag;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "otpRequest{" +
                "email='" + email + '\'' +
                ", flag='" + flag + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }

    public OtpRequest() {
        super();
    }
}
