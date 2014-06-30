package com.fpi.resource;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.fpi.exception.ServiceException;
import com.fpi.resource.obj.MessageBean;
import com.fpi.service.MessageBoardService;
import com.fpi.util.ValidationHelper;

/**
 * Implementation class of MessageBoard interface for protocols SOAP and REST.
 * 
 * This will call the Spring Service class that acts as an interface into the message 
 * board application from various protocols.
 * 
 * The input validation is done in the MessageBoard Service. The reason for this is to
 * have a common error validation regardles of the commonication protocols.
 *  
 *  
 * @see MessageBoard 
 * @author freddi
 *
 */
@WebService(endpointInterface = "com.fpi.resource.MessageBoard")
public class MessageBoardImpl implements MessageBoard {

	@Autowired
	private MessageBoardService mService;


	@Override
	public MessageBean createMessage(MessageBean message)  throws ServiceException {
		try {
			
		 mService.createMessage(message);
		} catch (ServiceException e) {
			ValidationHelper.throwValidationExceptionAsSoapOrRest(e);

		}
		return message;
	}
	
	@Override
	public List<MessageBean> listMessages() {
		return mService.listMessages();
	}

	@Override
	public List<MessageBean> listMessagesV2() {
		return mService.listMessagesV2();
	}
	@Override
	public void clear() {
    	mService.clear();
    	
    }
	@Override
    public MessageBean getMessage(Long messageId) throws ServiceException {
		try {
			return mService.getMessage(messageId);
		} catch (ServiceException e) {
			ValidationHelper.throwValidationExceptionAsSoapOrRest(e);
			return null;
		}
    }
    
	@Override
    public int update( MessageBean message) throws ServiceException {
		try {
			return mService.updateMessage(message);
		} catch (ServiceException e) {
			ValidationHelper.throwValidationExceptionAsSoapOrRest(e);
			return 0;
		}
    }
	@Override
    public MessageBean removeMessage(Long messageId) throws ServiceException {
		try {
			return mService.removeMessage(messageId);
		} catch (ServiceException e) {
			ValidationHelper.throwValidationExceptionAsSoapOrRest(e);
			return null;
		}
    	
    }
}
