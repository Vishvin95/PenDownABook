package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByName(String name);
}
