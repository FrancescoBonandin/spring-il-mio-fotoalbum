package org.java.spring.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.java.spring.db.auth.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 32, nullable=false )
	@Length(min=3, max=32)
	@NotBlank
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT", nullable= false)
	private String url;
	
	@Column(nullable = false)
	private boolean visible=true;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Category> categories;
	
	public Photo() {}
	
	public Photo(String title, String description, User user, String url, Category... categories) {
		
		setTitle(title);
		setDescription(description);
		setUser(user);
		setUrl(url);
		setCategories(categories);
		
	}
	
	public Photo(String title, String description, User user, String url, boolean visible, Category... categories) {
		
		setTitle(title);
		setDescription(description);
		setUser(user);
		setUrl(url);
		setVisible(visible);
		setCategories(categories);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
