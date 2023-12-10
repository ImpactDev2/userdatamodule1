package com.shopping.userDataModule1.response;

import java.util.List;
import java.util.Map;

public class ValidationErrorResponse {
	
	private String errorMessage;
	private Integer errorCode;
	private Map<String, String> errorData;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public Map<String, String> getErrorData() {
		return errorData;
	}
	public void setErrorData(Map<String, String> errors) {
		this.errorData = errors;
	}
	public ValidationErrorResponse(String errorMessage, Integer errorCode, Map<String, String> errorData) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorData = errorData;
	}
	public ValidationErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ValidationErrorResponse [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", errorData="
				+ errorData + "]";
	}
	
	
	
}
