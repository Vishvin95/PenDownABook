package com.pendownabook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.pendownabook.entities.Service;
import com.pendownabook.entities.Subscription;
import com.pendownabook.entities.User;
import com.pendownabook.repositories.ServiceRepository;
import com.pendownabook.repositories.SubscriptionRepository;
import com.pendownabook.repositories.UserRepository;

@org.springframework.stereotype.Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	private final static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@GetMapping
	public List<Subscription> getSubscription(String userEmail) {
		logger.info("Fetching Active Subscriptions");
		
		User user = userRepository.findByEmail(userEmail);
		List<Subscription> subscriptions = subscriptionRepository.findByUser(user);
		Iterator<Subscription> subscriptionIterator = subscriptions.iterator();
		while (subscriptionIterator.hasNext()) {
			if (!isActive(subscriptionIterator.next())) {
				subscriptionIterator.remove();
			}
		}

		return subscriptions;
	}

	private boolean isActive(Subscription subscription) {
		Service service = serviceRepository.findById(subscription.getService().getId()).get();
		int period = service.getServicePeriod();
		LocalDateTime paymentDate = subscription.getPaymentDate();
		
		if(LocalDateTime.now().isBefore(paymentDate.plusMonths(period)))
			return true;
		else
			return false;
	}

	public Subscription save(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public Subscription createOrder(Long serviceId, String email) {
		User user = userRepository.findByEmail(email);
		Service service = serviceRepository.findById(serviceId).get();

		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setService(service);
		subscription.setOrderId(getOrderId(20));
		logger.info("Subscrition Added");
		return subscriptionRepository.save(subscription);
	}

	private String getOrderId(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

//	RESULT:
//
//	{BANKNAME=HDFC Bank, 
//	BANKTXNID=777001772762081,  
//	CURRENCY=INR, 
//	GATEWAYNAME=HDFC, 
//	MID=ghXWDM27786985805008, 
//	ORDERID=vRsqJk3yoALfAlSqNFq5, 
//	PAYMENTMODE=DC, 
//	RESPCODE=01, 
//	RESPMSG=Txn Success, 
//	STATUS=TXN_SUCCESS, 
//	TXNAMOUNT=800.00, 
//	TXNDATE=2020-05-23 11:33:45.0, 
//	TXNID=20200523111212800110168260901559911}

	@Override
	public Subscription updateOrder(TreeMap<String, String> parameters) throws ParseException {
		Subscription subscription = subscriptionRepository.findByOrderId(parameters.get("ORDERID"));
		if (parameters.get("STATUS").equals("TXN_SUCCESS")) {
			subscription.setTransactionId(parameters.get("TXNID"));
			subscription.setCurrency(parameters.get("CURRENCY"));
			subscription.setAmount(parameters.get("TXNAMOUNT"));
			subscription.setStatus(parameters.get("STATUS"));

			String txnDateInString = parameters.get("TXNDATE");
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date date = ft.parse(txnDateInString);
			subscription.setPaymentDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			return subscriptionRepository.save(subscription);
		} else {
			subscriptionRepository.deleteByOrderId(subscription.getOrderId());
			return null;
		}
	}

}
