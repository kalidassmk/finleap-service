package com.finleap.notification.exception;


public class AppException  extends Exception{

	public AppException() {
		super();
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public AppException(String message) {
		super(message);
	}

}
