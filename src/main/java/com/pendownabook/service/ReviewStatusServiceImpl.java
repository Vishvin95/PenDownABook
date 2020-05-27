package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.ReviewStatus;
import com.pendownabook.repositories.ReviewStatusRepository;

@Service
public class ReviewStatusServiceImpl implements ReviewStatusService {

	@Autowired
	private ReviewStatusRepository reviewStatusRepository;

	@Override
	public List<ReviewStatus> getAll() {
		return reviewStatusRepository.findAll();
	}

	@Override
	public ReviewStatus getByReviewStatusById(Long reviewStatusId) {
		return reviewStatusRepository.findById(reviewStatusId).get();
	}

	@Override
	public ReviewStatus getByName(String name) {
		return reviewStatusRepository.findByName(name);
	}

	@Override
	public void saveAll(ArrayList<ReviewStatus> reviewStatuses) {
		reviewStatusRepository.saveAll(reviewStatuses);
	}
}