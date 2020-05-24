package com.pendownabook.service;

import java.text.ParseException;
import java.util.TreeMap;

import com.pendownabook.entities.Subscription;
import com.pendownabook.entities.User;

public interface SubscriptionService {
	public User findByEmail(String email);

	public Subscription createOrder(Long serviceId, String email);

	public Subscription updateOrder(TreeMap<String, String> parameters) throws ParseException;
}
