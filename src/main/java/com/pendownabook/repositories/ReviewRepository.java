package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.ReviewId;
import com.pendownabook.entities.Reviews;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, ReviewId>{

}
