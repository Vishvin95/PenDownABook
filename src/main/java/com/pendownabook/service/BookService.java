package com.pendownabook.service;

import java.util.List;

import com.pendownabook.entities.Book;

public interface BookService {
	public List<Book> getBooks();
	public List<Book> getBooks(String email);
	public Book save(Book book);
}
