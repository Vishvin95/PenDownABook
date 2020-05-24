package com.pendownabook.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.pendownabook.entities.PaytmDetails;
import com.pendownabook.entities.Service;
import com.pendownabook.entities.Subscription;
import com.pendownabook.entities.User;
import com.pendownabook.repositories.ServiceRepository;
import com.pendownabook.repositories.UserRepository;

@org.springframework.stereotype.Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaytmDetails paytmDetails;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public TreeMap<String, String> fillOrderDetails(Long serviceId, String email, Subscription subscription) throws Exception {			
		TreeMap<String, String> parameters = new TreeMap<>();
		User user = userRepository.findByEmail(email);
		Service service = serviceRepository.findById(serviceId).get();
		paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
		parameters.put("MOBILE_NO", user.getContact().toString());
		parameters.put("EMAIL", user.getEmail());
		parameters.put("TXN_AMOUNT", Double.toString(service.getServiceCost()));
		parameters.put("ORDER_ID", subscription.getOrderId());
		parameters.put("CUST_ID", user.getId().toString());
		String checkSum = getCheckSum(parameters);
		parameters.put("CHECKSUMHASH", checkSum);
		return parameters;
	}

	@Override
	public TreeMap<String, String> verifyPayment(Map<String, String[]> mapData, TreeMap<String, String> parameters) {
		String result = null;

		mapData.forEach((key, val) -> parameters.put(key, val[0]));
		String paytmChecksum = "";
		if (mapData.containsKey("CHECKSUMHASH")) {
			paytmChecksum = mapData.get("CHECKSUMHASH")[0];
		}

		boolean isValidChecksum = false;
		System.out.println("RESULT : " + parameters.toString());
		try {
			isValidChecksum = validateCheckSum(parameters, paytmChecksum);
			if (!isValidChecksum) {
				result = "Checksum mismatched";
			}
		} catch (Exception e) {
			result = e.toString();
		}

		parameters.remove("CHECKSUMHASH");
		return parameters;
	}

	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
		return CheckSumServiceHelper.getCheckSumServiceHelper()
				.verifycheckSum(paytmDetails.getMerchantKey().substring(1), parameters, paytmChecksum);
	}

	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return CheckSumServiceHelper.getCheckSumServiceHelper()
				.genrateCheckSum(paytmDetails.getMerchantKey().substring(1), parameters);
	}	
}
