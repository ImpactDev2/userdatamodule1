package com.shopping.userDataModule1.response;

public class Response {
    private Integer statusCode;
    private String responseMessage;

    public Response() {
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

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
