package com.fpi.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.fpi.domain.Message;
/**
 * A simple in memory MessageBoard DAO.
 * 
 * @see MessageBoardDAO
 * @author freddi
 *
 */
@Repository
public class MemoryMessageBoardDAOImpl implements MessageBoardDAO {

	private Map<Long,Message> allMessages=Collections.synchronizedMap(new HashMap<Long,Message>());
	private AtomicLong id=new AtomicLong(1);
	
	/**
	 * Returns an <b>immutable</b> list of all message
	 * 
	 * @return a list of Messages. Any changes to the returned list is not reflected to the internal in memory list
	 */
	@Override
	public List<Message> listMessages() {
		List<Message> localList=new ArrayList<Message>(allMessages.size());
		
		
		for(Message m: allMessages.values()) {
			localList.add(new Message(m));
		}
		
		
		return localList;

	}	
	
	@Override
	public long addMessage(Message message) {
		Long newId=id.getAndIncrement();
		message.setMessageId(newId);
		allMessages.put(newId, message);
		
		return newId;
	
	}
	@Override
	public void clear() {
		allMessages.clear();
	}
	@Override
	public Message getMessage(Long id) {
		return allMessages.get(id);
	}
	@Override
	public int updateMessage(Message message) {
		if(allMessages.containsKey(message.getMessageId())) {
			allMessages.put(message.getMessageId(), message);
			return 1;
		} else {
			return 0;
		}
		
	}
	@Override
	public Message removeMessage(Long id) {
		return allMessages.remove(id);
	}

}
