package com.pendownabook.service;

import java.util.HashSet;

import com.pendownabook.entities.Role;

public interface RoleService {
	public Role getByName(String names);

	public void saveAll(HashSet<Role> roles);
	
}
