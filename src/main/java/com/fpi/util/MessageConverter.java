package com.fpi.util;

import java.util.ArrayList;
import java.util.List;

import com.fpi.domain.Message;
import com.fpi.resource.obj.MessageBean;
import com.fpi.resource.obj.MessageBeanVersion;

/**
 * Used to convert the internal domain representation between different external message versions 
 * 
 * 
 * @author freddi
 *
 */
public class MessageConverter {



	public static List<MessageBean> messageToMessageBean(List<Message> messages,MessageBeanVersion version) {

		// returns an empty list in case input list was null
		if(messages==null) {
			return new ArrayList<MessageBean>();
		}
		
		List<MessageBean> newList=new ArrayList<MessageBean>(messages.size());
		for(Message m: messages) {
			MessageBean messageBean=MessageConverter.messageToMessageBean(m,version);
			newList.add(messageBean);
			
		}
		return newList;
	}

	public static MessageBean messageToMessageBean(Message message,MessageBeanVersion version) {
		MessageBean messageBean=new MessageBean();
		switch (version) {
		case V2:
			messageBean.setUrl(message.getUrl());
		case V1:
			messageBean.setId(message.getMessageId());
			messageBean.setTitle(message.getTitle());
			messageBean.setContent(message.getContent());
			messageBean.setSender(message.getSender());

		}
		return messageBean;
		
	}



}
