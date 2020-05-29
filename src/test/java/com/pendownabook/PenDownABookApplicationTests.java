package com.pendownabook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pendownabook.entities.Publisher;
import com.pendownabook.repositories.PublisherRepository;
import com.pendownabook.service.PublisherService;
import com.pendownabook.service.PublisherServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
class PenDownABookApplicationTests {
	
	@InjectMocks
	private PublisherService publisherService = new PublisherServiceImpl();
	
	@Mock
	private PublisherRepository publisherRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	void shouldListAllPublishers() throws Exception {		
		List<Publisher> list = new ArrayList<Publisher>();
		
        Publisher pubOne = new Publisher();        
        pubOne.setId(1l);
        pubOne.setFirstName("A");
        pubOne.setEmail("a@gmail.com");
        
        Publisher pubTwo = new Publisher();        
        pubTwo.setId(2l);
        pubTwo.setFirstName("B");
        pubTwo.setEmail("b@gmail.com");
         
        list.add(pubOne);
        list.add(pubTwo);
         
        when(publisherRepository.findAll()).thenReturn(list);
         
        List<Publisher> pubList = publisherService.getAll();
         
        assertEquals(2, pubList.size());
        verify(publisherRepository, times(1)).findAll();
	}

}