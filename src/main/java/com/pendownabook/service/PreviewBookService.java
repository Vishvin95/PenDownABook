package com.pendownabook.service;

import java.text.ParseException;
import java.util.List;

import com.pendownabook.entities.PreviewBook;
import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.ReviewStatus;

public interface PreviewBookService {
	public List<PreviewBook> getAll();

	public List<PreviewBook> getPreviewBook(String email);

	public PreviewBook savePreviewBook(String email, Long genreId, String previewBookTitle, String previewBookPath)
			throws ParseException;

	public List<Object> getReviewsForPublisher(String publisherEmail);

	public void addPublisherReviewForPreviewBook(Long previewBookId, ReviewStatus reviewStatus, Publisher publisher) throws ParseException;
}
