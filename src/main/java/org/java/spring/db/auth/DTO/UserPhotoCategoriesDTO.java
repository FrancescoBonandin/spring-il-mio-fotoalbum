package org.java.spring.db.auth.DTO;

import java.util.Arrays;
import java.util.List;

import org.java.spring.pojo.Category;

public class UserPhotoCategoriesDTO {

	private String title;
	
	private String description;
	
	private String url;
	
	private int photographer_id;
	
	private List<Category> categories;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPhotographer_id() {
		return photographer_id;
	}

	public void setPhotographer_id(int photographer_id) {
		this.photographer_id = photographer_id;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setCategories(Category... categories) {
		setCategories(Arrays.asList(categories)); 
	}
	
}
