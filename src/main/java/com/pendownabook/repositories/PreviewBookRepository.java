package com.pendownabook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pendownabook.entities.PreviewBook;
import com.pendownabook.entities.User;
import com.pendownabook.repositories.custom.PreviewBookRepositoryCustom;

public interface PreviewBookRepository extends JpaRepository<PreviewBook, Long>, PreviewBookRepositoryCustom {
	public List<PreviewBook> findByUser(User user);
}
