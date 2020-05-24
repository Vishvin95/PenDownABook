package com.pendownabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Book;
import com.pendownabook.repositories.BookRepository;
import com.pendownabook.repositories.UserRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getBooks(String email) {
		return bookRepository.findByUser(userRepository.findByEmail(email));
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}
}
