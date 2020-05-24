package com.pendownabook.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pendownabook.entities.User;
import com.pendownabook.service.UserService;

@RestController
@RequestMapping("/home")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/profile")
	public User getUser(@RequestParam(name = "email") String email) {
		return userService.findByEmail(email);
	}
}