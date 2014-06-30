package com.fpi.service;

import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpi.dao.MessageBoardDAO;
import com.fpi.domain.Message;
import com.fpi.exception.ServiceException;
import com.fpi.resource.obj.MessageBean;
import com.fpi.resource.obj.MessageBeanVersion;
import com.fpi.util.MessageConverter;
import com.fpi.util.ValidationHelper;

@Service
public class SimpleMessageBoardServiceImpl implements MessageBoardService {

	@Autowired
	private MessageBoardDAO messageBoardDAO;
	
	/**
	 * As Spring and Apache CXF doesn't support the java validation annotation 
	 * in other than Controller or CXF web services classes I need to explicitly call the validation API. 
	 */
	@Autowired
    private Validator validator;
	
	@Override
	public MessageBean createMessage(MessageBean message) throws ServiceException {

		ValidationHelper.validateBean(validator, message);
		
		long id=messageBoardDAO.addMessage(new Message(message.getTitle(), message.getContent(), message.getSender(),message.getUrl()));
		message.setId(id);
		return message;
		
	}
	@Override
	public List<MessageBean> listMessages() {
		return MessageConverter.messageToMessageBean(messageBoardDAO.listMessages(),MessageBeanVersion.V1);
	}
	@Override
	public List<MessageBean> listMessagesV2() {
		return MessageConverter.messageToMessageBean(messageBoardDAO.listMessages(),MessageBeanVersion.V2);
	}
	@Override
	public void clear() {
		messageBoardDAO.clear();
	}

	@Override
	public MessageBean getMessage(Long id) throws ServiceException{
		ValidationHelper.validateMessageId(id);
		Message message=messageBoardDAO.getMessage(id);
		if(message==null) {
			throw new ServiceException("No message found for id " + id,null);
		}
		return MessageConverter.messageToMessageBean(message, MessageBeanVersion.V2);
	}
	
	@Override
	public int updateMessage(MessageBean messageBean) throws ServiceException{
		
		ValidationHelper.validateBean(validator, messageBean);
		ValidationHelper.validateMessageId(messageBean.getId());
		Message message=new Message();
		message.setMessageId(messageBean.getId());
		message.setTitle(messageBean.getTitle());
		message.setSender(messageBean.getSender());
		message.setContent(messageBean.getContent());
		message.setUrl(messageBean.getUrl());
		return messageBoardDAO.updateMessage(message);
		
	}
	@Override
	public MessageBean removeMessage(Long id) throws ServiceException {

		ValidationHelper.validateMessageId(id);
		Message message=messageBoardDAO.removeMessage(id);
		if(message==null) {
			throw new ServiceException("No message found for id " + id,null);
		}
		return MessageConverter.messageToMessageBean(message, MessageBeanVersion.V2);

	}

}
