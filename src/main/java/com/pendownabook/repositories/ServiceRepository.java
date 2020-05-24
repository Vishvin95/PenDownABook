package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	public Service findByServiceCode(String serviceCode);
}
