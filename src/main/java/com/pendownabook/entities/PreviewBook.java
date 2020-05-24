package com.pendownabook.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PreviewBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 128)
	private String title;

	@Column(length = 256)
	private String pdfPath;

	@OneToOne
	private Genre genre;

	private LocalDateTime dateOfUpload;

	@JsonBackReference(value = "previewbook_user")
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@JsonManagedReference(value = "review_preview_book")
	@OneToMany(mappedBy = "previewBook")
	private Set<Reviews> reviews;

	public PreviewBook() {

	}

	public PreviewBook(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Reviews> reviews) {
		this.reviews = reviews;
	}

	public LocalDateTime getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(LocalDateTime dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}
}
