package com.pendownabook.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pendownabook.entities.Publisher;
import com.pendownabook.entities.User;
import com.pendownabook.service.PublisherService;
import com.pendownabook.service.UserService;
import com.pendownabook.web.controllers.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
	private UserService userService;

	@Autowired
	private PublisherService publisherService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "register";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
			BindingResult result) {

		User existingUser = userService.findByEmail(userDto.getEmail());
		if (existingUser != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		} else {
			Publisher existingPublisher = publisherService.getByEmail(userDto.getEmail());
			if (existingPublisher != null)
				result.rejectValue("email", null, "There is already an account registered with that email");
		}

		if (result.hasErrors()) {
			return "register";
		} else {
			userService.save(userDto);
			return "redirect:/registration?success";
		}
	}
}