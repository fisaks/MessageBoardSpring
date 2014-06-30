package com.fpi.exception;

import java.io.Serializable;

public class ExceptionDetails implements Serializable {

	private static final long serialVersionUID = -2561637153290474117L;

	private String faultMessage;

	public ExceptionDetails() {
	}


	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}

}
