package org.java.spring.controller;

import java.util.List;

import org.java.spring.pojo.Category;
import org.java.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryServ;

	
	@GetMapping("categories")
	
	public String routeIndex(Model model) {
		
		List<Category> categories = categoryServ.findAll();
							
		
		model.addAttribute("categories", categories);
		
		
		return "categories/index";
		
	}
	
	@GetMapping("/demigod/categories/create")
	
	public String routeCreate(Model model) {
		
		Category category = new Category();
		
		model.addAttribute("category", category);
		model.addAttribute("title", "Create");
		
		return "categories/form";
	}
	
	@PostMapping("/demigod/categories/create")
	public String storeCategory(
			Model model,
			@Valid @ModelAttribute Category category, 
			BindingResult bindingResult) {

			model.addAttribute("category", category);
		
			return saveCategory(model,category,bindingResult);
	}
	
	
	@GetMapping("/demigod/categories/edit/{id}")
	public String routeEdit(Model model, @PathVariable Long id) {
		
		Category category = categoryServ.findById(id);
		
		model.addAttribute("category",category);
		model.addAttribute("title", "Edit");
		
		return "categories/form";
	}
	
	@PostMapping("/demigod/categories/edit/{id}")
	public String updateCategory(
			Model model,
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult,
			@PathVariable Long id) {	
			
			return saveCategory(model,category,bindingResult);
			
		}
	
	@PostMapping("/demigod/categories/delete/{id}")
	public String routeDelete( RedirectAttributes redirectAttribute,  @PathVariable Long id) {
		
		Category category = categoryServ.findById(id);
		
		categoryServ.delete(category);
		
		redirectAttribute.addFlashAttribute("deletedCategory", category);
		
		
		return "redirect:/categories";
	}

	private String saveCategory(Model model,
			@Valid @ModelAttribute Category category, 
			BindingResult bindingResult) {
		
		System.out.println("Category:\n" + category);
		System.out.println("\n---------------\n");
		System.out.println("Error:\n" + bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("category", category);
			return "categories/form";
		}
		
		try {
			
			categoryServ.save(category);
		} catch(Exception e) {
			
			model.addAttribute("category", category);
			return "categories/form";
		}
		
		return "redirect:/categories";
		
	}
}
