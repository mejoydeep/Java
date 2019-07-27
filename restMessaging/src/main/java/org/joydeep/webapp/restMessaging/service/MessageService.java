package org.joydeep.webapp.restMessaging.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.joydeep.webapp.restMessaging.database.DatabaseClass;
import org.joydeep.webapp.restMessaging.exception.DataNotFoundException;
import org.joydeep.webapp.restMessaging.model.Message;

public class MessageService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1,"Hello World !","Joydeep"));
		messages.put(2L, new Message(2,"Hello Bengaluru !","Joy"));
	}
	
	public List<Message> getAllMessages(){
		/*
		 * Message m1=new Message(1L,"Hello World !","Joydeep"); 
		 * Message m2=new Message(2L, "Hello New Jersey", "Joydeep");
		 * 
		 * List<Message> list=new ArrayList<>(); list.add(m1); list.add(m2); return
		 * list;
		 */
		return new ArrayList<Message>(messages.values());
		
	}
	
	//Return particular messages by year
	public List<Message> getAllMessageForYear(int year){
		List<Message> messageForYear=new ArrayList<Message>();
		Calendar cal=Calendar.getInstance();
		for(Message message:messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messageForYear.add(message);
			}
			
		}
		return messageForYear;
	}
	//Return particular message by starting index and size
	public List<Message> getAllMessagePaginated(int start, int size){
		ArrayList<Message> list=new ArrayList<Message>(messages.values());
		if(start+size>messages.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	//Get a particular message  service class
	public Message getMessage(long id) {
		Message message= messages.get(id);
		if(message== null) {
			throw new DataNotFoundException("Message with id: "+id+" not found");
		} 	
			return message;
		
	}
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		messages.put(message.getId(),message);// it creates a new message if the message doesn't exist, use replace method instead.
		return message;
		
	}
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
