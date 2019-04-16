package com.zelin.exception;

/**
 * 自定义异常类
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	//代表异常的错误信息
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public MyException() {
		super();
	}

	public MyException(String message) {
		super();
		this.message = message;
	}
	
}
