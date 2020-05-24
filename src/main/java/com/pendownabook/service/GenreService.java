package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import com.pendownabook.entities.Genre;

public interface GenreService {

	public Genre getByName(String string);

	public void saveAll(ArrayList<Genre> genres);

	public List<Genre> getAll();

	public void saveGenre(Genre genre);

	public void deleteGenreById(Long id);

}
