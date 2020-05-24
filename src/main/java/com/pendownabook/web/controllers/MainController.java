package com.pendownabook.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
    public String root(HttpServletRequest request) {
		return "index";
    }
	
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
    	if(request.isRequestedSessionIdValid())
			return "userHome";
		else
			return "index";
    }
	
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
