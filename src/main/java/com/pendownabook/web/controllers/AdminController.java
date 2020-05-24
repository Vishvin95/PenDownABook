package com.pendownabook.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pendownabook.entities.Genre;
import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.Service;
import com.pendownabook.service.CustomerService;
import com.pendownabook.service.GenreService;
import com.pendownabook.service.PublisherService;
import com.pendownabook.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private PublisherService publisherService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService custService;

	private boolean isProfileRedirected = false;
	private boolean isPublisherRedirected = false;
	private boolean isGenreRedirected = false;
	private boolean isServiceRedirected = false;

	@RequestMapping
	public ModelAndView home(HttpServletRequest request, Authentication authentication) {
		ModelAndView adminHome = new ModelAndView();
		if (request.isRequestedSessionIdValid()) {
			adminHome.clear();
			if (isProfileRedirected) {
				adminHome.addObject("myProfile", userService.findByEmail(authentication.getName()));
				isProfileRedirected = false;
			} else if (isPublisherRedirected) {
				adminHome.addObject("existingPublishers", publisherService.getAll());
				adminHome.addObject("newPublisher", new Publisher());
				isPublisherRedirected = false;
			} else if (isGenreRedirected) {
				adminHome.addObject("existingGenres", genreService.getAll());
				adminHome.addObject("newGenre", new Genre());
				isGenreRedirected = false;
			} else if (isServiceRedirected) {
				adminHome.addObject("existingServices", custService.getAllServices());
				adminHome.addObject("newService", new Service());
				isServiceRedirected = false;
			}
			adminHome.addObject("accountFor", authentication.getName());
			adminHome.setViewName("adminHome");
			return adminHome;
		} else {
			return new ModelAndView("index");
		}
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			isProfileRedirected = true;
			return "redirect:/admin";
		} else
			return "index";
	}

	@GetMapping("/publisher")
	public String getPublishers(HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			isPublisherRedirected = true;
			return "redirect:/admin";
		} else
			return "index";
	}

	@PostMapping("/savepublisher")
	public String savePublisher(Publisher publisher, HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			publisherService.savePublisher(publisher);
			return "redirect:/admin/publisher";
		} else
			return "index";
	}

	@RequestMapping(path = "/deletepublisher/{id}")
	public String deletePublisherById(Model model, @PathVariable("id") Long id) {
		publisherService.deletePublisherById(id);
		return "redirect:/admin/publisher";
	}

	@GetMapping("/genre")
	public String getAllGenres(HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			isGenreRedirected = true;
			return "redirect:/admin";
		} else
			return "index";
	}

	@PostMapping("/savegenre")
	public String saveGenre(Genre genre, HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			genreService.saveGenre(genre);
			return "redirect:/admin/genre";
		} else
			return "index";
	}

	@RequestMapping(path = "/deletegenre/{id}")
	public String deleteGenreById(Model model, @PathVariable("id") Long id) {
		genreService.deleteGenreById(id);
		return "redirect:/admin/genre";
	}

	@GetMapping("/service")
	public String getAllServices(HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			isServiceRedirected = true;
			return "redirect:/admin";
		} else
			return "index";
	}

	@PostMapping("/saveservice")
	public String saveService(Service service, HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			custService.saveService(service);
			return "redirect:/admin/service";
		} else
			return "index";
	}

	@RequestMapping(path = "/deleteservice/{id}")
	public String deleteServiceById(Model model, @PathVariable("id") Long id) {
		custService.deleteServiceById(id);
		return "redirect:/admin/service";
	}

}
