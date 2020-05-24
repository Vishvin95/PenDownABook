package com.pendownabook.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.Role;
import com.pendownabook.repositories.PublisherRepository;
import com.pendownabook.repositories.RoleRepository;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Publisher> getAll() {		
		return publisherRepository.findAll();
	}

	@Override
	public Publisher getByEmail(String email) {
		Publisher publisher = publisherRepository.findByEmail(email);
		return publisher;
	}

	@Override
	public void saveAll(ArrayList<Publisher> publishers) {
		publishers = (ArrayList<Publisher>) publisherRepository.saveAll(publishers);		
		for (Publisher publisher : publishers)
			publisher = addPublisherRoles(publisher);
		publisherRepository.saveAll(publishers);
	}

	@Override
	public void deletePublisherById(Long id) {
		Publisher publisher =publisherRepository.findById(id).get();
		publisher.setRoles(null);
		publisherRepository.save(publisher);
		publisherRepository.deleteById(id);		
	}

	@Override
	public void savePublisher(Publisher publisher) {
		publisher.setPassword(passwordEncoder.encode(publisher.getPassword()));
		publisher = addPublisherRoles(publisher);
		publisherRepository.save(publisher);		
	}

	public Publisher addPublisherRoles(Publisher publisher)
	{
		HashSet<Role> publisherRoles = new HashSet<>();
		Role publisherRole = roleRepository.findByName("PUBLISHER");
		publisherRoles.add(publisherRole);
		publisher.setRoles(publisherRoles);
		return publisher;
	}
	
}
