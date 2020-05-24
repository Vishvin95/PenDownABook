package com.pendownabook.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.Genre;
import com.pendownabook.service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping
	public List<Genre> getAllGenres() {
		return genreService.getAll();
	}
}
