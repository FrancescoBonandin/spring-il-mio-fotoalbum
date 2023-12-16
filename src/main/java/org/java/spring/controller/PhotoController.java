package org.java.spring.controller;

import java.util.List;

import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.auth.pojo.User;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoServ;
	
	@Autowired
	private CategoryService categoryServ;
	
	@Autowired
	private UserService userServ;
	
//	@GetMapping
//	
//	public String routeIndex(Model model, @RequestParam(required=false) String q ) {
//		
//		List<Photo> photos = q==null
//							? photoServ.findAll()
//							: photoServ.findByTitleOrDescription(q);
//		
//		model.addAttribute("photos", photos);
//		model.addAttribute("q", q == null ? "" : q);
//		
//		return "photos/index";
//		
//	}
	@GetMapping
	
	public String routeIndex(Model model, @RequestParam(required=false) String q, @RequestParam(required=false) Category[] checked ) {
		
		User user = getAuthUser();
		List <Category> allCategories=categoryServ.findAll();
		System.out.println("user: "+ user.getUsername());
		List<Photo> photos = q == null && checked==null
							? photoServ.findAllByUserId(user.getId())
							: (checked == null? photoServ.findByUserIdAndTitleOrDescription(q, user.getId())
								:(q == null? photoServ.filterByCategories(user.getId(), checked)
										: photoServ.filterAndFind(q, user.getId(), checked)));
		
		model.addAttribute("photos", photos);
		model.addAttribute("categories", allCategories);
		model.addAttribute("q", q == null ? "" : q);
		model.addAttribute("checked", checked == null ? new Category[0]:checked);
		
		return "photos/index";
		
	}
	

	
	@GetMapping("/photos/{id}")
	public String routeShow(Model model, @PathVariable Long id) {
		
		Photo photo = photoServ.findById(id);
		
		model.addAttribute("photo",photo);
		
		return "photos/show";
		
	}
	
	@GetMapping("/demigod/photos/create")
	
	public String routeCreate(Model model) {
		
		Photo photo = new Photo();
		List <Category> categories=categoryServ.findAll();
		
		model.addAttribute("photo",photo);
		model.addAttribute("categories", categories);
		model.addAttribute("title", "Create");
		
		return "photos/form";
	}
	
	@PostMapping("/demigod/photos/create")
	public String storePhoto(
			Model model,
			@Valid @ModelAttribute Photo photo, 
			BindingResult bindingResult) {
			
			User user = getAuthUser();
			System.out.println("USER "+user.getUsername());
			photo.setUser(user);

			model.addAttribute("photo", photo);
			model.addAttribute("title", "Create");
			return savePhoto(model,photo,bindingResult);
		}
	
	@GetMapping("/demigod/photos/edit/{id}")
	public String routeEdit(Model model, @PathVariable Long id) {
		
		Photo photo = photoServ.findById(id);
		List <Category> categories=categoryServ.findAll();
		
		model.addAttribute("photo",photo);
		model.addAttribute("categories", categories);
		model.addAttribute("title", "Edit");
		
		return "photos/form";
	}
	
	@PostMapping("/demigod/photos/edit/{id}")
	public String updatePhoto(
			Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult,
			@PathVariable Long id) {
			
			model.addAttribute("title", "Edit");
			User user = getAuthUser();
			Photo befEditPhoto=photoServ.findById(id);
			if(befEditPhoto.getUser().equals(user)) {
				System.out.println("USER "+user.getUsername());
				photo.setUser(user);
				return savePhoto(model,photo,bindingResult);
			}
			
			else {
				return "photos/form";
			}
		}
	
	@PostMapping("/demigod/photos/delete/{id}")
	public String routeDelete( RedirectAttributes redirectAttribute,  @PathVariable Long id) {
		
		Photo photo = photoServ.findById(id);
		
		photoServ.delete(photo);
		
		redirectAttribute.addFlashAttribute("deletedPhoto", photo);
		
		
		return "redirect:/";
	}
	
	
	private String savePhoto(Model model,
			@Valid @ModelAttribute Photo photo, 
			BindingResult bindingResult) {
		
		System.out.println("Photo:\n" + photo);
		System.out.println("\n---------------\n");
		System.out.println("Error:\n" + bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("photo", photo);
			return "photos/form";
		}
		
		try {
			
			photoServ.save(photo);
		} catch(Exception e) {
			
			model.addAttribute("photo", photo);
			return "photos/form";
		}
		
		return "redirect:/";
		
	}
	
	private User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return  userServ.findByUsername(username);
	}
	

}
