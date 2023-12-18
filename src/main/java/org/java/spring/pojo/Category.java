package org.java.spring.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 32, nullable=false )
	@Length(min=3, max=32)
	@NotBlank
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Photo> photos;
	
	public Category() {}
	
	public Category(String name, String description,Photo...photos ) {
		
		setName(name);
		setDescription(description);
		setPhotos(photos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void setPhotos(Photo... photos ) {
		setPhotos(Arrays.asList(photos));
	}
	
	
	
}
