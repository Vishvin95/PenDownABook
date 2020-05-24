package com.pendownabook.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long publisherId;
	private Long previewBookId;

	public ReviewId() {

	}

	public ReviewId(Long publisherId, Long previewBookId) {
		super();
		this.publisherId = publisherId;
		this.previewBookId = previewBookId;
	}

	public Long getpublisherId() {
		return publisherId;
	}

	public void setpublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public Long getpreviewBookId() {
		return previewBookId;
	}

	public void setpreviewBookId(Long previewBookId) {
		this.previewBookId = previewBookId;
	}
	
	@Override
	public boolean equals(Object obj) {
		ReviewId reviewId = (ReviewId) obj;
		if(this.previewBookId == reviewId.getpreviewBookId() &&
				this.publisherId == reviewId.getpublisherId())
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
}
