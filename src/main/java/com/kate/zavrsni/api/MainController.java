package com.kate.zavrsni.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kate.zavrsni.model.Review;
import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.ReviewRepository;
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
	
	@Autowired
	private ReviewRepository reviewRepository;

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
		
		Comparator<Wine> compareByRating = (Wine w1, Wine w2) -> w1.getRating().compareTo(w2.getRating());
		Collections.sort(wines, compareByRating.reversed());
		
		List<Wine> top8 = wines.subList(0, 8);
		
		model.addAttribute("wines", top8);	
		
		
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
		
		List<Double> ratings = new ArrayList<>();
		List<Review> allReviews = new ArrayList<>(reviewRepository.findAll());
		
		for (int i = 0; i < owners.size(); i++) {
			Double rating = 0.0;
			int nOfRating = 0;
			
			if (owners.get(i).getRole() == true) {
				wineries.add(owners.get(i));
				
				if (allReviews.isEmpty() == false && allReviews != null) {
					for (int j = 0; j < allReviews.size(); j++) {
						if (allReviews.get(j).getWine().getOwner().getId() == owners.get(i).getId()) {
							nOfRating++;
							rating += allReviews.get(j).getRating();
						}
					}
				}
			}
			ratings.add(rating/nOfRating);
		}

		model.addAttribute("wineries", wineries);	
		model.addAttribute("ratings", ratings);
		
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
