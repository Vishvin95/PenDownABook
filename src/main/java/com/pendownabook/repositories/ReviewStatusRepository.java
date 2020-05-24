package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pendownabook.entities.ReviewStatus;

public interface ReviewStatusRepository extends JpaRepository<ReviewStatus, Long> {
	public ReviewStatus findByName(String name);
}