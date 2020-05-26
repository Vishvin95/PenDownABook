package com.pendownabook.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	public List<Book> getBooks() {
		logger.info("Finding book For Purchase");
		return bookRepository.findAll();
	}

	public List<Book> getBooks(String email) {
		return bookRepository.findByUser(userRepository.findByEmail(email));
	}

	public Book save(Book book) {
		logger.info("Book Saved");
		return bookRepository.save(book);
	}

}
