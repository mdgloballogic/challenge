package com.bci.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.bci.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	
	@Builder.Default
    private UUID id = UUID.randomUUID();
	private String username;
    private String email;
    private String password;
    private boolean isActive;
    private LocalDate created;
    private LocalDate lastLogin;
    private LocalDate modified;
    private String token;
    private Set<Phone> phones;
    
    public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public LocalDate getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDate lastLogin) {
		this.lastLogin = lastLogin;
	}
	public LocalDate getModified() {
		return modified;
	}
	public void setModified(LocalDate modified) {
		this.modified = modified;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}    
}
