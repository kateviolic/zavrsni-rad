package com.kate.zavrsni.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.UserRepository;
import com.kate.zavrsni.repository.WineRepository;
import com.kate.zavrsni.service.UserService;
import com.kate.zavrsni.service.WineService;

@Controller
public class MainController {
	
	@Autowired
	private WineService wineService;
	
	@Autowired
	private WineRepository wineRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		List<Wine> wines = wineRepository.findAll();
		
		if (wines.isEmpty())
			model.addAttribute("empty", false);
		
		Collections.reverse(wines);
		//top8 vina
		// footer?
		model.addAttribute("wines", wines);	
		
		
		return "home";
	}
	
	@GetMapping("/wineries")
	public String wineries(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		List<User> owners = userRepository.findAll();
		List<User> wineries = new ArrayList<>();
		 
		if (owners.isEmpty())
			model.addAttribute("empty", false);
		
		for (int i = 0; i < owners.size(); i++) {
			if (owners.get(i).getRole() == true)
				wineries.add(owners.get(i));
		}

		model.addAttribute("wineries", wineries);	
		
		
		return "wineries";
	}
	
	@GetMapping("/winery/{id}")
	public String winery(@PathVariable Long id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		List<Wine> wines = wineRepository.findAll();
		List<Wine> winesByWinery = new ArrayList<>();
		User winery = userRepository.getReferenceById(id);
		
		for (int i = 0; i < wines.size(); i++) {
			if (winery.getId() == wines.get(i).getOwner().getId())
				winesByWinery.add(wines.get(i));
		}
		
		if (winesByWinery.isEmpty())
			model.addAttribute("empty", true);

		model.addAttribute("winery", winery);
		model.addAttribute("wines", winesByWinery);	
		model.addAttribute("profil", false);	
		
		return "profileOwner";
	}
	
}
