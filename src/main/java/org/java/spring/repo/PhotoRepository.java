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
	
	public abstract List<Photo> findByTitleContainingOrDescriptionContainingAndUserId(String title, String description,Long id) ;
	
	public abstract List<Photo> findByCategoriesContaining(Category...categories);

	public abstract List<Photo> findByTitleContainingOrDescriptionContainingAndCategoriesContaining(String title, String description, Category...categories) ;
}
