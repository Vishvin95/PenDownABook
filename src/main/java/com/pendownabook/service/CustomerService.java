package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import com.pendownabook.entities.Service;

public interface CustomerService {
	
	public List<Service> getAllServices();

	public Service getByServiceCode(String string);

	public void saveAll(ArrayList<Service> services);

	public void saveService(Service service);

	public void deleteServiceById(Long id);

}
