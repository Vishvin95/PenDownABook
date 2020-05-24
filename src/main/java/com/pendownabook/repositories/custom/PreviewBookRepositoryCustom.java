package com.pendownabook.repositories.custom;

import java.util.List;

public interface PreviewBookRepositoryCustom {
	public List<Object> findPreviewBooksLeftJoinPublisher(String publisherEmail);
}
