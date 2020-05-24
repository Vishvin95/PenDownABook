package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	public Publisher findByEmail(String email);
}
