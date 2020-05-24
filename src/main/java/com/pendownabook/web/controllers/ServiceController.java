package com.pendownabook.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.Service;
import com.pendownabook.service.CustomerService;

@RestController
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping
	public List<Service> getAll()
	{
		return service.getAllServices();
	}
}
