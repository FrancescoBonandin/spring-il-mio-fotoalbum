package org.java.spring.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.java.spring.db.auth.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	@Column(length = 32, nullable=false )
	@Email
	@Length(min=3, max=32)
	@NotBlank
	private String email;
	
	@Column(columnDefinition = "TEXT")
	@Length(min=3)
	@NotBlank
	private String body;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
	private User user;
	
	public Message() {}
	
	public Message(String email, String body, User user) {
		
		setEmail(email);
		setBody(body);
		setUser(user);
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(new Date().getTime());
        
        
    } 
	
 
}
