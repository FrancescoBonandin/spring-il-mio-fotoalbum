package org.java.spring.controller;

import java.util.List;

import org.java.spring.pojo.Message;
import org.java.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageServ;

	
	@GetMapping("/demigod/messages")
	
	public String routeIndex(Model model) {
		
		List<Message> messages = messageServ.findAll();
							
		
		model.addAttribute("messages", messages);
		
		
		return "messages/index";
		
	}
	
	@GetMapping("/demigod/messages/{id}")
		
		public String routeShow(Model model,@PathVariable Long id) {
			
			Message message = messageServ.findById(id);
								
			
			model.addAttribute("message", message);
			
			
			return "messages/show";
			
		}
		
	
	@PostMapping("/demigod/messages/delete/{id}")
	public String routeDelete( RedirectAttributes redirectAttribute,  @PathVariable Long id) {
		
		Message message = messageServ.findById(id);
		
		messageServ.delete(message);
		
		redirectAttribute.addFlashAttribute("deletedMessage", message);
		
		
		return "redirect:/messages";
	}

//	private String saveMessage(Model model,
//			@Valid @ModelAttribute Message message, 
//			BindingResult bindingResult) {
//		
//		System.out.println("Message:\n" + message);
//		System.out.println("\n---------------\n");
//		System.out.println("Error:\n" + bindingResult);
//		
//		if (bindingResult.hasErrors()) {
//			
//			model.addAttribute("message", message);
//			return "messages/form";
//		}
//		
//		try {
//			
//			messageServ.save(message);
//		} catch(Exception e) {
//			
//			model.addAttribute("message", message);
//			return "messages/form";
//		}
//		
//		return "redirect:/messages";
//		
//	}
}
