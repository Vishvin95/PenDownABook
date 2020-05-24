package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Genre;
import com.pendownabook.repositories.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;

	@Override
	public Genre getByName(String name) {
		Genre genre = genreRepository.findByName(name);
		return genre;
	}

	@Override
	public void saveAll(ArrayList<Genre> genres) {
		genreRepository.saveAll(genres);
	}

	@Override
	public List<Genre> getAll() {
		return genreRepository.findAll();
	}

	@Override
	public void saveGenre(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public void deleteGenreById(Long id) {
		genreRepository.deleteById(id);		
	}

}
