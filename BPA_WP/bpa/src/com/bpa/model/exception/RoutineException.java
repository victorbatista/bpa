package com.bpa.model.exception;

public class RoutineException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	
	public RoutineException(String _message) {
		this.message = _message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
