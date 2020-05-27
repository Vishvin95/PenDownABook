package com.pendownabook.repositories.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pendownabook.entities.Publisher;
import com.pendownabook.repositories.PublisherRepository;
import com.pendownabook.repositories.custom.PreviewBookRepositoryCustom;

@Repository
@Transactional
public class PreviewBookRepositoryCustomImpl implements PreviewBookRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PublisherRepository publisherRepository;;
	
	@Override
	public List<Object> findPreviewBooksLeftJoinPublisher(String publisherEmail) {
		
		Publisher publisher = publisherRepository.findByEmail(publisherEmail);
		
		Query query = entityManager.createNativeQuery("Select " + 
				"	preview_book_id as previewBookId,   " + 
				"	date_of_upload as dateOfUpload,   " + 
				"	pdf_path as pdfPath,  " + 
				"	title as title,  " + 
				"	user.first_name as firstName,  " + 
				"	user.last_name as lastName,  " + 
				"	genre.name as genre,  " + 
				"	publisher_id as publisherId,  " + 
				"	review_status_id as reviewStatusId   " + 			
				"	from " + 
				"	(select * from reviews union select date_of_review,id2,id1,review_status_id from " +
				"   (select publisher.id as id1, preview_book.id as id2 from publisher, preview_book where (publisher.id,preview_book.id) not in " +
				"   (select reviews.publisher_id as id1, reviews.preview_book_id as id2 from reviews)) as t " +
				"   left join reviews on t.id1 = reviews.publisher_id and t.id2 = reviews.preview_book_id) as p " + 
				"   left join publisher on p.publisher_id = publisher.id " + 
				"   left join preview_book on p.preview_book_id = preview_book.id" + 
				"   left join user on preview_book.user_id = user.id" + 
				"   left join genre on preview_book.genre_id = genre.id" +
				"   left join review_status on review_status_id = review_status.id" +
				"   where publisher_id = " + publisher.getId() + 
				"   and review_status.name not in (\"Accepted\",\"Rejected\");"
				);		
		
		return query.getResultList();
	}
}
