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

	@RequestMapping
	public ModelAndView home(HttpServletRequest request, Authentication authentication) {
		return getCommonModel(request, authentication);
	}

	@GetMapping("/profile")
	public ModelAndView profile(HttpServletRequest request, Authentication authentication) {

		ModelAndView adminHome = getCommonModel(request, authentication);
		if (adminHome.hasView()) {
			adminHome.addObject("myProfile", userService.findByEmail(authentication.getName()));
			adminHome.addObject("accountFor", authentication.getName());
		}
		return adminHome;
	}

	@GetMapping("/publisher")
	public ModelAndView getPublishers(HttpServletRequest request, Authentication authentication) {
		ModelAndView adminHome = getCommonModel(request, authentication);
		if (adminHome.getViewName().equals("adminHome")) {
			adminHome.addObject("existingPublishers", publisherService.getAll());
			adminHome.addObject("newPublisher", new Publisher());
			adminHome.addObject("accountFor", authentication.getName());
		}
		return adminHome;
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
	public ModelAndView getAllGenres(HttpServletRequest request, Authentication authentication) {
		ModelAndView adminHome = getCommonModel(request, authentication);
		if (adminHome.getViewName().equals("adminHome")) {
			adminHome.addObject("existingGenres", genreService.getAll());
			adminHome.addObject("newGenre", new Genre());
			adminHome.addObject("accountFor", authentication.getName());
		}
		return adminHome;
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
	public ModelAndView getAllServices(HttpServletRequest request, Authentication authentication) {
		ModelAndView adminHome = getCommonModel(request, authentication);
		if (adminHome.getViewName().equals("adminHome")) {
			adminHome.addObject("existingServices", custService.getAllServices());
			adminHome.addObject("newService", new Service());
		}
		return adminHome;
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

	private ModelAndView getCommonModel(HttpServletRequest request, Authentication authentication) {
		if (request.isRequestedSessionIdValid()) {
			return new ModelAndView("adminHome").addObject("accountFor", authentication.getName());
		} else {
			return new ModelAndView("index");
		}
	}
}
