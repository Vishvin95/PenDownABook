package com.pendownabook.repositories.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pendownabook.repositories.custom.PreviewBookRepositoryCustom;

@Repository
@Transactional
public class PreviewBookRepositoryCustomImpl implements PreviewBookRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Object> findPreviewBooksLeftJoinPublisher(String publisherEmail) {
		
		Query query = entityManager.createNativeQuery("select " + 
				"preview_book.id as previewBookId, " + 
				"preview_book.date_of_upload as dateOfUpload, " + 
				"preview_book.pdf_path as pdfPath," + 
				"preview_book.title as title," + 
				"user.first_name as firstName," + 
				"user.last_name as lastName," + 
				"genre.name as genre," + 
				"publisher.id as publisherId," + 
				"reviews.review_status_id as reviewStatusId " + 
				"from preview_book " + 
				"left join reviews on preview_book.id = reviews.preview_book_id " + 
				"inner join user on user.id = preview_book.user_id " + 
				"inner join genre on genre.id = preview_book.genre_id " + 
				"left join publisher on reviews.publisher_id = publisher.id " + 
				"where publisher.id is null or " + 
				"publisher.email = \"" + publisherEmail + "\";");		
		
		return query.getResultList();
	}
}
