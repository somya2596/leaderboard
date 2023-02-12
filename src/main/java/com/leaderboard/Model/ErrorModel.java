package com.leaderboard.Model;

import java.io.Serializable;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private Integer status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ErrorModel(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorModel [message=" + message + ", status=" + status + "]";
	}

}
