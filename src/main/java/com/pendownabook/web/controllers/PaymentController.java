package com.pendownabook.web.controllers;

import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pendownabook.entities.PaytmDetails;
import com.pendownabook.entities.Subscription;
import com.pendownabook.service.PaymentService;
import com.pendownabook.service.SubscriptionService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaytmDetails paytmDetails;

	@PostMapping("/plan/{serviceId}/{emailId}")
	public ModelAndView doPayment(@PathVariable(name = "serviceId") Long serviceId,
			@PathVariable(name = "emailId") String email) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
		Subscription subscription =  subscriptionService.createOrder(serviceId,email);		
		TreeMap<String, String> parameters = paymentService.fillOrderDetails(serviceId, email, subscription);
		modelAndView.addAllObjects(parameters);
		return modelAndView;
	}

	@PostMapping("/validate")
	public String validatePayment(HttpServletRequest request, Model model) throws ParseException {
		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();

		String result = null;
		parameters = paymentService.verifyPayment(mapData, parameters);
		if(parameters.get("RESPCODE").equals("01"))			
			result = "Payment Successful";
		else
			result = "Payment Failed";
		
		Subscription subscription = subscriptionService.updateOrder(parameters);		
		model.addAttribute("subscription", subscription);
		model.addAttribute("result", result);
		model.addAttribute("parameters", parameters);
		return "report";
	}
}
