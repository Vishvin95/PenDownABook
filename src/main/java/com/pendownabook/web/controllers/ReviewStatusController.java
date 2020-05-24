package com.pendownabook.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.ReviewStatus;
import com.pendownabook.service.ReviewStatusService;

@RestController
@RequestMapping("/reviewstatus")
public class ReviewStatusController {
	@Autowired
	private ReviewStatusService reviewStatusService;

	@GetMapping
	public List<ReviewStatus> getAllReviewStatus() {
		return reviewStatusService.getAll();
	}
}