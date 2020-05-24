package com.pendownabook.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.Book;
import com.pendownabook.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/showAll")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	@GetMapping("/showbyemail")
	public List<Book> getBookForUser(@RequestParam(name = "email") String email){
		return bookService.getBooks(email);
	}
	
	@RequestMapping(value = "/createbook/{email}", method = RequestMethod.POST)
	public Book createBook(@RequestBody Book book, @PathVariable("email") String email) {			
        return bookService.save(book);
    }
}