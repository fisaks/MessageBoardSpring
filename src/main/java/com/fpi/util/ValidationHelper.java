package com.fpi.util;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;

import com.fpi.exception.ExceptionDetails;
import com.fpi.exception.ServiceException;
import com.fpi.resource.obj.MessageBean;

public class ValidationHelper {

	
	public static void validateMessageId(Long messageId) throws ServiceException {
		if(messageId==null) {
			ExceptionDetails allExceptionDetails[] = new ExceptionDetails[1];
			ExceptionDetails exceptionDetails = new ExceptionDetails();
			exceptionDetails.setFaultMessage("The Message id can not be null");
			allExceptionDetails[0] = exceptionDetails;
			throw new ServiceException("Fault Message",allExceptionDetails);

		}
		
	}
	public static void validateBean(Validator validator,MessageBean message) throws ServiceException {
		Set<ConstraintViolation<MessageBean>> constraintViolations=validator.validate(message);
		
		if(constraintViolations.size()>0) {
			ExceptionDetails allExceptionDetails[] = new ExceptionDetails[constraintViolations.size()];
			int i=0;
			for(ConstraintViolation<?> violation:constraintViolations) {
				ExceptionDetails exceptionDetails = new ExceptionDetails();
				exceptionDetails.setFaultMessage( violation.getPropertyPath() + " " + violation.getMessage());
				allExceptionDetails[i++] = exceptionDetails;
			}
		 throw new ServiceException("Fault Message",allExceptionDetails);
		}
		
	}
	
	public static void throwValidationExceptionAsSoapOrRest(ServiceException serviceException) throws ServiceException {
	    Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest servletRequest = (HttpServletRequest) message.get("HTTP.REQUEST");
	    String mediaType=servletRequest.getHeader("Accept");
	    if(mediaType==null) {
	    	mediaType=servletRequest.getContentType();
	    }
	    
	    if (MediaType.APPLICATION_JSON.equals(mediaType) || 
	    	MediaType.APPLICATION_XML.equals(mediaType) ) {

	      javax.ws.rs.core.Response.ResponseBuilder builder = Response.status
	                                      (javax.ws.rs.core.Response.Status.BAD_REQUEST);
	      builder.type(mediaType);
	      if(serviceException.getFaultDetails()!=null)
	    	  builder.entity(serviceException.getFaultDetails());
	      else
	    	  builder.entity(serviceException.getMessage());
	      throw new WebApplicationException(builder.build());
	    } else {
	      throw serviceException;
	    }
	  }
}
