package org.java.spring.db.auth.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.java.spring.pojo.Message;
import org.java.spring.pojo.Photo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	
	private Long id;
	
	
	@NotNull
	@Column(nullable = false)
	
	private String username;
	
	@NotNull
	@Column(nullable = false)
	
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Photo> photos;
	
	@OneToMany(mappedBy = "user")
	private List<Message> messages;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public User() {}
	
	public User(String username, String password, Role... roles) {
		
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setRoles(Role... roles) {
		setRoles(Set.of(roles));
	}
	
	
	
	public List<Photo> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
			.toList();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
	
	
	