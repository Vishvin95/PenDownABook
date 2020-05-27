package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import com.pendownabook.entities.ReviewStatus;

public interface ReviewStatusService {
	public List<ReviewStatus> getAll();

	public ReviewStatus getByReviewStatusById(Long reviewStatusId);

	public ReviewStatus getByName(String string);

	public void saveAll(ArrayList<ReviewStatus> reviewStatuses);
}