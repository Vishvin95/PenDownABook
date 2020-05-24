package com.pendownabook.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pendownabook.service.UserService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

	@Autowired
	private UserService userService;

	private boolean isProfileRedirected = false;
	
	@RequestMapping
	public ModelAndView home(HttpServletRequest request, Authentication authentication) {
		ModelAndView publisherHome = new ModelAndView();
		if (request.isRequestedSessionIdValid()) {
			publisherHome.clear();
			if (isProfileRedirected) {
				publisherHome.addObject("myProfile", userService.findByEmail(authentication.getName()));
				isProfileRedirected = false;
			}
			publisherHome.addObject("accountFor", authentication.getName());
			publisherHome.setViewName("publisherHome");
			return publisherHome;
		} else {
			return new ModelAndView("index");
		}
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
		if (request.isRequestedSessionIdValid()) {
			isProfileRedirected = true;
			return "redirect:/publisher";
		} else
			return "index";
	}
}