package com.pendownabook.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Role;
import com.pendownabook.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getByName(String name) {
		Role role = roleRepository.findByName(name);
		return role;
	}

	@Override
	public void saveAll(HashSet<Role> roles) {
		roleRepository.saveAll(roles);

	}

}
