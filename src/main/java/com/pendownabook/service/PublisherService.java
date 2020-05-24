package com.pendownabook.service;

import java.util.ArrayList;
import java.util.List;

import com.pendownabook.entities.Publisher;

public interface PublisherService {
	
	public List<Publisher> getAll();
	
	public Publisher getByEmail(String string);

	public void saveAll(ArrayList<Publisher> publishers);

	public void deletePublisherById(Long id);

	public void savePublisher(Publisher publisher);

}
