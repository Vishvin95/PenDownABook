package com.pendownabook.service;

import java.util.Map;
import java.util.TreeMap;

import com.pendownabook.entities.Subscription;

public interface PaymentService {

	public TreeMap<String, String> fillOrderDetails(Long serviceId, String email, Subscription subscription) throws Exception;

	public TreeMap<String, String> verifyPayment(Map<String, String[]> mapData, TreeMap<String, String> parameters);

}
