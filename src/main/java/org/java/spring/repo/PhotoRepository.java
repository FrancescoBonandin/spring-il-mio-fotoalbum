package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	public abstract List<Photo>findAllByUserId(Long id);
	
	public abstract List<Photo> findByTitleContainingOrDescriptionContaining(String title, String description) ;
	
	public abstract List<Photo> findByTitleContainingAndUserIdOrDescriptionContainingAndUserId(String title,Long id, String description,Long id2) ;
	
	public abstract List<Photo> findByUserIdAndCategoriesIn(Long id,List<Category> categories);

	public abstract List<Photo> findByTitleContainingAndUserIdOrDescriptionContainingAndUserIdAndCategoriesIn(String title,Long id, String description,Long id2, List<Category> categories) ;

	  @Query("SELECT DISTINCT p FROM Photo p " +
	           "JOIN p.categories c " +
	           "WHERE (LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))) " +
	           "AND p.user.id = :userId " +
	           "AND c IN :categories")
	    List<Photo> filterPhotos(@Param("query") String query, @Param("userId") Long userId, @Param("categories") List<Category> categories);
} 
 