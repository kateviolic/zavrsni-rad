package com.kate.zavrsni.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.UserRepository;
import com.kate.zavrsni.repository.WineRepository;
import com.kate.zavrsni.service.WineService;

@Controller
@RequestMapping("/profile")
public class UserController {
	
	@Autowired
	WineService wineService;
	
	@Autowired
	WineRepository wineRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public String profile(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		if (currentUser.getRole().equals(false))
			return "profileUser";
		
		List<Wine> wines = wineRepository.findAll();
		List<Wine> winesByWinery = new ArrayList<>();
		
		for (int i = 0; i < wines.size(); i++) {
			if (currentUser.getId() == wines.get(i).getOwner().getId())
				winesByWinery.add(wines.get(i));
		}
		
		if (winesByWinery.isEmpty())
			model.addAttribute("empty", true);

		model.addAttribute("wines", winesByWinery);
		model.addAttribute("profil", true);
		
		return "profileOwner";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		return "editUser";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, BindingResult result, Model model) {
		model.addAttribute("user", user);
		
		if (result.hasErrors()) {
			return "redirect:/profile/edit?error";
        }
		
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty() == false) {
			for (int i = 0; i < allUsers.size(); i++) {
				if(allUsers.get(i).getId() != user.getId()) {
					if (allUsers.get(i).getEmail().equals(user.getEmail()))
						return "redirect:/user/edit?emailError";
					if (allUsers.get(i).getUsername().equals(user.getUsername()))
						return "redirect:/user/edit?usernameError";
				}
			}
		}
		
		User saveUser;
		if (userRepository.existsById(user.getId()))
			saveUser = userRepository.getReferenceById(user.getId());
		else
			saveUser = new User();
		
		saveUser.setId(user.getId());
		saveUser.setUsername(user.getUsername());
		saveUser.setGender(user.getGender());
		saveUser.setFirstName(user.getFirstName());
		saveUser.setLastName(user.getLastName());
		saveUser.setEmail(user.getEmail());
		saveUser.setDescription(user.getDescription());
		
		userRepository.save(saveUser);
		
		model.addAttribute("message", "Your changes have been saved! ");
		return "redirect:/profile";
	}
}
