package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Genre;
import com.pendownabook.repositories.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;
	
	private final static Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);
	
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
		logger.info("Fetched all Genres");
		return genreRepository.findAll();
	}

	@Override
	public void saveGenre(Genre genre) {
		logger.info("Genre Added");
		genreRepository.save(genre);
	}

	@Override
	public void deleteGenreById(Long id) {
		logger.info("Genre Deleted");
		genreRepository.deleteById(id);		
	}

}
