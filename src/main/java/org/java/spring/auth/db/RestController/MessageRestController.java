package org.java.spring.auth.db.RestController;

import org.java.spring.auth.db.service.UserService;
import org.java.spring.pojo.Message;
import org.java.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/message/")
public class MessageRestController {
	
	@Autowired
	private MessageService messageServ;
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("create")
	public ResponseEntity<Message> routeCreate(@RequestBody Message message) {
		message.setUser(userServ.findById(1));
		messageServ.save(message);
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
