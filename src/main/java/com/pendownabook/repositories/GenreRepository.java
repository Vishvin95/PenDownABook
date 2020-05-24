package com.pendownabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pendownabook.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	public Genre findByName(String name);

}
