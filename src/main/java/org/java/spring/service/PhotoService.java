package org.java.spring.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.java.spring.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PhotoService {
	
	@Autowired
	private	PhotoRepository photoRepo;
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	
	public Photo findById(Long id) {
		
		return photoRepo.findById(id).get();
	}
	
	public List<Photo> findByTitleOrDescription(String q) {
		
		return photoRepo.findByTitleContainingOrDescriptionContaining(q,q);
	}
	
	public List<Photo> findByUserIdAndTitleOrDescription(String q, Long id) {
		
		return photoRepo.findByTitleContainingAndUserIdOrDescriptionContainingAndUserId(q,id,q,id);
	}
	
	public List<Photo> filterByCategories(Long id, Category...categories){
			
		return photoRepo.findByUserIdAndCategoriesContaining(id, categories);
	}
	
	public	List<Photo> findAllByUserId(Long id) {
		
		return photoRepo.findAllByUserId(id);
	}
	
	public List<Photo> filterAndFind(String q, Long id, Category...categories){
		
		return photoRepo.findByTitleContainingAndUserIdOrDescriptionContainingAndUserIdAndCategoriesContaining(q,id,q,id, categories);
	}
	
	@Transactional
	public Photo findPhotoWithCategories(Long id){
		
		Optional<Photo> photoOpt =photoRepo.findById(id);
		
		if (!photoOpt.isPresent()) {
			return null;
		}
		
		Photo photo = photoOpt.get();
		
		Hibernate.initialize(photo.getCategories());
		
		return photo;
	}
	
	public void save(Photo photo) {
		
		photoRepo.save(photo);
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
	
	
	public void deleteById(Long id) {
		
		photoRepo.deleteById(id);
		
	}
}
