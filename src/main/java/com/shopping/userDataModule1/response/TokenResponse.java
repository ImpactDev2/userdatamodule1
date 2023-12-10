package com.shopping.userDataModule1.response;

public class TokenResponse {

    private Integer statusCode;
    private String responseMessage;
    private String token;

    public TokenResponse() {
        super();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "statusCode=" + statusCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
