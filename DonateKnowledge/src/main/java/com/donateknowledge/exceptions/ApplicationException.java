package com.donateknowledge.exceptions;

import org.springframework.web.servlet.NoHandlerFoundException;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public ApplicationException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		
	}

	public ApplicationException(Exception e) {
		super(e);
		if (e instanceof NoHandlerFoundException) {
			errCode = "404";
			errMsg = e.getMessage();
		}
	}



}