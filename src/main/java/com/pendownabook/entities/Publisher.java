package com.pendownabook.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(uniqueConstraints = { 
			@UniqueConstraint(columnNames = "publisherLicenseNumber") 
		})
@PrimaryKeyJoinColumn(name="publisherId")
public class Publisher extends User{
	
	@Column(length = 128, nullable = false)
	private String publisherFirm;

	@Column(unique = true, length = 20, nullable = false)
	private String publisherLicenseNumber;

	@JsonManagedReference(value = "publisher_reviews")
	@OneToMany(mappedBy = "publisher")
	private Set<Reviews> reviews;

	public Publisher() {

	}

	public Publisher(String publisherFirm, String publisherLicenceNumber) {		
		this.publisherFirm = publisherFirm;		
		this.publisherLicenseNumber = publisherLicenceNumber;		
	}

	public String getPublisherFirm() {
		return publisherFirm;
	}

	public void setPublisherFirm(String publisherFirm) {
		this.publisherFirm = publisherFirm;
	}

	public String getPublisherLicenseNumber() {
		return publisherLicenseNumber;
	}

	public void setPublisherLicenseNumber(String publisherLicenseNumber) {
		this.publisherLicenseNumber = publisherLicenseNumber;
	}	
}
