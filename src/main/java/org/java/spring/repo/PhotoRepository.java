package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	public abstract List<Photo>findAllByUserId(Long id);
	
	public abstract List<Photo> findByTitleContainingOrDescriptionContaining(String title, String description) ;
	
	public abstract List<Photo> findByTitleContainingAndUserIdOrDescriptionContainingAndUserId(String title,Long id, String description,Long id2) ;
	
	public abstract List<Photo> findByUserIdAndCategoriesContaining(Long id,Category...categories);

	public abstract List<Photo> findByTitleContainingAndUserIdOrDescriptionContainingAndUserIdAndCategoriesContaining(String title,Long id, String description,Long id2, Category... categories) ;
} 
 