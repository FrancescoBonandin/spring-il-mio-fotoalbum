package org.java.spring.service;

import java.util.List;

import org.java.spring.pojo.Category;
import org.java.spring.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> findAll(){
		
		return categoryRepo.findAll();
	}
	
	public Category findById(long id){
		
		return categoryRepo.findById(id).get();
	}
	

	public void save(Category category) {
		
		categoryRepo.save(category);
	}
	
	public void delete(Category category) {
		
		categoryRepo.delete(category);
	}
	
	
	public void deleteById(Long id) {
		
		categoryRepo.deleteById(id);
		
	}
}
