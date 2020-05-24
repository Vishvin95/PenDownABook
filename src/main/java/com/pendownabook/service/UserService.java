package com.pendownabook.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pendownabook.entities.User;
import com.pendownabook.web.controllers.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	public User findByEmail(String email);

	public User save(UserRegistrationDto registration);
	
	public User saveAdmin(User user);
}
