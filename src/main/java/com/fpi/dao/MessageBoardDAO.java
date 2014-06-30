package com.fpi.dao;

import java.util.List;

import com.fpi.domain.Message;

public interface MessageBoardDAO {

	
	/**
	 * 
	 * @return A list of the messages in the message board app
	 */
	public List<Message> listMessages();
	
	/**
	 * Adds the newMessage to the list
	 * 
	 * @param message
	 */
	public long addMessage(Message message);
	
	/**
	 * Clear all messages on the board
	 */
	public void clear();
	
	/**
	 * Returns the message with the id 
	 * @param id
	 * @return
	 */
	public Message getMessage(Long id);
	
	/**
	 * Update the message and returns the count of updated messages.
	 * 0 is returned in case the message was not found
	 * 
	 * @param message
	 * @return
	 */
	public int updateMessage(Message message);

	/**
	 * Removes and returns the message with the id
	 * @param id
	 * @return
	 */
	public Message removeMessage(Long id);
}
