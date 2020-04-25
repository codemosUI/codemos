package com.component.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class StatusMessage {

	private Integer status;
	private String message;
	
	public StatusMessage() {
	}

	@JsonProperty(value = "status_code")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonProperty(value = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}