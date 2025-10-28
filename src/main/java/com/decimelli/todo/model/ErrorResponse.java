package com.decimelli.todo.model;

public class ErrorResponse {

	private String error;

	public ErrorResponse(String errorMessage) {
		this.error = errorMessage;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
}