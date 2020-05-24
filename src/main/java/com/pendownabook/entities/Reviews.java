package com.pendownabook.entities;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Reviews {

	@EmbeddedId
	private ReviewId reviewId = new ReviewId();

	@JsonBackReference(value = "publisher_reviews")
	@ManyToOne
	@MapsId("publisherId")
	private Publisher publisher;

	@JsonBackReference(value = "review_preview_book")
	@ManyToOne
	@MapsId("previewBookId")
	private PreviewBook previewBook;

	private LocalDateTime dateOfReview;

	@OneToOne
	private ReviewStatus reviewStatus;

	public Reviews() {

	}

	public ReviewId getReviewId() {
		return reviewId;
	}

	public void setReviewId(ReviewId reviewId) {
		this.reviewId = reviewId;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public PreviewBook getPreviewBook() {
		return previewBook;
	}

	public void setPreviewBook(PreviewBook previewBook) {
		this.previewBook = previewBook;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public LocalDateTime getDateOfReview() {
		return dateOfReview;
	}

	public void setDateOfReview(LocalDateTime dateOfReview) {
		this.dateOfReview = dateOfReview;
	}
}
