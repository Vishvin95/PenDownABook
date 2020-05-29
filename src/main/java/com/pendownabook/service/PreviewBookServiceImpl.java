package com.pendownabook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Genre;
import com.pendownabook.entities.PreviewBook;
import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.ReviewId;
import com.pendownabook.entities.ReviewStatus;
import com.pendownabook.entities.Reviews;
import com.pendownabook.entities.User;
import com.pendownabook.repositories.GenreRepository;
import com.pendownabook.repositories.PreviewBookRepository;
import com.pendownabook.repositories.ReviewRepository;
import com.pendownabook.repositories.UserRepository;

@Service
public class PreviewBookServiceImpl implements PreviewBookService {
	@Autowired
	private PreviewBookRepository previewBookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	private final static Logger logger = LoggerFactory.getLogger(PreviewBookServiceImpl.class);

	@Override
	public List<PreviewBook> getAll() {
		logger.info("Finding all Preview books");
		return previewBookRepository.findAll();
	}

	@Override
	public List<PreviewBook> getPreviewBook(String email) {
		logger.info("Finding Preview books");
		return previewBookRepository.findByUser(userRepository.findByEmail(email));
	}

	@Override
	public PreviewBook savePreviewBook(String email, Long genreId, String previewBookTitle, String previewBookPath)
			throws ParseException {
		User user = userRepository.findByEmail(email);
		Genre genre = genreRepository.findById(genreId).get();

		PreviewBook previewBook = new PreviewBook();
		previewBook.setGenre(genre);
		previewBook.setUser(user);
		previewBook.setTitle(previewBookTitle);
		previewBook.setPdfPath(previewBookPath);

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String formatDateTime = now.format(format);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = ft.parse(formatDateTime);
		previewBook.setDateOfUpload(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		logger.info("Creating a new Preview Book");
		return previewBookRepository.save(previewBook);
	}

	@Override
	public List<Object> getReviewsForPublisher(String publisherEmail) {
		return previewBookRepository.findPreviewBooksLeftJoinPublisher(publisherEmail);
	}

	@Override
	public void addPublisherReviewForPreviewBook(Long previewBookId, ReviewStatus reviewStatus, Publisher publisher)
			throws ParseException {
		// Check, if review already exists for the publisher
		ReviewId reviewId = new ReviewId();
		reviewId.setpublisherId(publisher.getId());
		reviewId.setpreviewBookId(previewBookId);

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String formatDateTime = now.format(format);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = ft.parse(formatDateTime);

		Optional<Reviews> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent()) {
			Reviews review = new Reviews();
			review.setReviewStatus(reviewStatus);
			review.setPublisher(publisher);
			review.setPreviewBook(previewBookRepository.findById(previewBookId).get());
			review.setDateOfReview(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			reviewRepository.save(review);
			logger.info("Review Added By Publisher: " + reviewStatus.getName());
		} else {
			Reviews reviews = opt.get();
			reviews.setReviewStatus(reviewStatus);
			reviews.setDateOfReview(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			reviewRepository.save(reviews);
			logger.info("Review Status Changed By Publisher");
		}
	}
}
