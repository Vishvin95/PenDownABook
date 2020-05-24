package com.pendownabook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pendownabook.entities.Book;
import com.pendownabook.entities.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByUser(User findByEmail);
}
