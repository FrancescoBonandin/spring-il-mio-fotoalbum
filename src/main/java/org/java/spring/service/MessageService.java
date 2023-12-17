package org.java.spring.service;

import java.util.List;

import org.java.spring.pojo.Message;
import org.java.spring.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepo;
	
	public List<Message> findAll(){
		
		return messageRepo.findAll();
	}
	
	public Message findById(long id){
		
		return messageRepo.findById(id).get();
	}
	

	public void save(Message message) {
		
		messageRepo.save(message);
	}
	
	public void delete(Message message) {
		
		messageRepo.delete(message);
	}
	
	
	public void deleteById(Long id) {
		
		messageRepo.deleteById(id);
		
	}
}