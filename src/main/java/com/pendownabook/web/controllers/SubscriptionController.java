package com.pendownabook.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.Subscription;
import com.pendownabook.service.SubscriptionServiceImpl;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionServiceImpl subscriptionService;

	@GetMapping("/subscriptiondetails")
	public List<Subscription> getSubscriptionForUser(@RequestParam(name = "email") String email) {
		return subscriptionService.getSubscription(email);
	}

	@PostMapping("/createsubscription")
	public Subscription createSubscription(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.save(subscription);
	}
}
