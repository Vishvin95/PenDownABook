package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pendownabook.entities.Service;
import com.pendownabook.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private ServiceRepository serviceRepository;
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public List<Service> getAllServices() {
		return serviceRepository.findAll();
	}

	@Override
	public Service getByServiceCode(String serviceCode) {
		Service service = serviceRepository.findByServiceCode(serviceCode);
		return service;
	}

	@Override
	public void saveAll(ArrayList<Service> services) {
		serviceRepository.saveAll(services);
	}

	@Override
	public void saveService(Service service) {
		logger.info("Service Added");
		serviceRepository.save(service);
	}

	@Override
	public void deleteServiceById(Long id) {
		logger.info("Service Deleted");
		serviceRepository.deleteById(id);
	}
}
