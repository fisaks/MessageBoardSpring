package com.fpi.service;

import java.util.List;

import com.fpi.exception.ServiceException;
import com.fpi.resource.obj.MessageBean;

/**
 * The MessageBoard Service or Facade that other transport protocols
 * will call to interact with the message board application.
 *  
 * @author freddi
 *
 */
public interface MessageBoardService {
	
	public MessageBean createMessage(MessageBean message) throws ServiceException ;
	
	public List<MessageBean> listMessages();
	public List<MessageBean> listMessagesV2();
	
	public void clear();
	
	public MessageBean getMessage(Long id) throws ServiceException;
	
	public int updateMessage(MessageBean message) throws ServiceException;
	
	public MessageBean removeMessage(Long id) throws ServiceException;

}
