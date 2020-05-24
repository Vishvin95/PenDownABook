package com.pendownabook.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pendownabook.entities.Role;
import com.pendownabook.entities.User;
import com.pendownabook.repositories.RoleRepository;
import com.pendownabook.repositories.UserRepository;
import com.pendownabook.web.controllers.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);
	}

	@Override
	public User findByEmail(String email) {		
		User user = userRepository.findByEmail(email);		
		return user;
	}

	@Override
	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setContact(registration.getContact());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		
		HashSet<Role> userRoles = new HashSet<>();
		userRoles.add(roleRepository.findByName("USER"));
		user.setRoles(userRoles);
		return userRepository.save(user);
	}

	@Override
	public User saveAdmin(User user) {		
		user = userRepository.save(user);				
		Role adminRole = roleRepository.findByName("ADMIN");
		HashSet<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		user.setRoles(adminRoles);
		return userRepository.save(user);
	}

}
