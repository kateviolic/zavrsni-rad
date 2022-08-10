package com.kate.zavrsni.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kate.zavrsni.dto.UserRegistrationDto;
import com.kate.zavrsni.model.User;
import com.kate.zavrsni.repository.UserRepository;
import com.kate.zavrsni.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;
	
	@Autowired
	UserRepository userRepository;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/registration?error";
		}
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty() == false) {
			for (int i = 0; i < allUsers.size(); i++) {
				if (allUsers.get(i).getEmail().equals(registrationDto.getEmail()))
					return "redirect:/registration?usernameError";
				if (allUsers.get(i).getEmail().equals(registrationDto.getEmail()))
					return "redirect:/registration?emailError";
			}
		}
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
