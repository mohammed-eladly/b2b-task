package com.b2b.food.group.helpers;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResultBean {
	private boolean isSuccess;
	private HttpStatus statusCode;
	private String errorMessage;
	private Object result;

	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultBean(boolean isSuccess, HttpStatus statusCode, String errorMessage, Object result) {
		super();
		this.isSuccess = isSuccess;
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.result = result;
	}

}
