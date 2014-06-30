package com.fpi.exception;

public class ServiceException extends Exception  {
	 
	private static final long serialVersionUID = 8392534244270496606L;
	
	private ExceptionDetails faultDetails[];

	  public ServiceException(ExceptionDetails faultDetails[]) {
	    this.faultDetails = faultDetails;
	  }

	  public ServiceException(String message, ExceptionDetails faultDetails[]) {
	    super(message);
	    this.faultDetails = faultDetails;
	  }

	  public ExceptionDetails[] getFaultDetails() {
	    return faultDetails;
	  }

}
