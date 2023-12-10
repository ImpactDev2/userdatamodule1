package com.shopping.userDataModule1.response;

public class OtpResponse {
    private String OTP;
    private String expiresAt;
    private String createdAt;
    private String flag;
    private String status;
    private String statusCode;
    private String message;


    public OtpResponse(String OTP, String expiresAt, String createdAt, String flag, String status, String statusCode,String message) {
        this.OTP = OTP;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.flag = flag;
        this.status = status;
        this.statusCode = statusCode;
        this.message= message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "OtpResponse{" +
                "OTP='" + OTP + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", flag='" + flag + '\'' +
                ", status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }

    public OtpResponse() {
        super();
    }
}
