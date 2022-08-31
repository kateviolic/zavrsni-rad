package com.kate.zavrsni.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kate.zavrsni.model.Review;
import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.ReviewRepository;
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
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@GetMapping
	public String profile(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		if (currentUser.getRole().equals(false)) {
			List<Review> reviews = reviewRepository.findAll();
			List<Review> reviewByUser = new ArrayList<>();
			
			for (int i = 0; i < reviews.size(); i++) {
				if (currentUser.getId() == reviews.get(i).getUser().getId())
					reviewByUser.add(reviews.get(i));
			}
			
			if (reviewByUser.isEmpty())
				model.addAttribute("empty", true);
			
			Collections.reverse(reviewByUser);
			model.addAttribute("reviews", reviewByUser);
			
			return "profileUser";
		}
			
		
		List<Wine> wines = wineRepository.findAll();
		List<Wine> winesByWinery = new ArrayList<>();
		
		for (int i = 0; i < wines.size(); i++) {
			if (currentUser.getId() == wines.get(i).getOwner().getId())
				winesByWinery.add(wines.get(i));
		}
		
		if (winesByWinery.isEmpty())
			model.addAttribute("empty", true);
		
		Collections.reverse(wines);
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
	
	@GetMapping("/statistics")
	public String stats(@RequestParam (defaultValue = "usernameAsc") String sort, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);

		List<Review> reviews = new ArrayList<>();		
		List<Review> sortedReviews = reviewRepository.findAll();
		
		if(sort.equals("usernameAsc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "user.username"));
		if(sort.equals("usernameDesc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "user.username"));
		if(sort.equals("wineAsc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "wine.name"));
		if(sort.equals("wineDesc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "wine.name"));
		if(sort.equals("dateAsc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
		if(sort.equals("dateDesc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
		if(sort.equals("ratingAsc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "rating"));
		if(sort.equals("ratingDesc")) 
			sortedReviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
		
		for (int i = 0; i < sortedReviews.size(); i++) {
			if (currentUser.getId() == sortedReviews.get(i).getWine().getOwner().getId())
				reviews.add(sortedReviews.get(i));
		}

		LocalDate todayDate = LocalDate.now();
		LocalDate monthThis = LocalDate.of(todayDate.getYear(), todayDate.getMonth(), 01);
		LocalDate monthPrevious;
		
		if (monthThis.getMonthValue() - 1 == 0)
			monthPrevious = LocalDate.of(todayDate.getYear()-1, 12, 01);
		else
			monthPrevious = LocalDate.of(todayDate.getYear(), todayDate.getMonthValue() - 1, 01);
		
		
		Double thisMonth = 0.0;
		int nOfThisMonth = 0;
		for (int i = 0; i < reviews.size(); i++) {
			if (reviews.get(i).getDate().isAfter(monthThis) || reviews.get(i).getDate().isEqual(todayDate)) {
				nOfThisMonth++;
				thisMonth += reviews.get(i).getRating();
			}
		}
		
		thisMonth = thisMonth / nOfThisMonth;
		model.addAttribute("thisMonth", Math.round(thisMonth*100.0)/100.0);
		
		Double previousMonth = 0.0;
		int nOfPreviousMonth = 0;
		for (int i = 0; i < reviews.size(); i++) {
			if ((reviews.get(i).getDate().isAfter(monthPrevious) || reviews.get(i).getDate().isEqual(monthPrevious)) && reviews.get(i).getDate().isBefore(monthThis)) {
				nOfPreviousMonth++;
				previousMonth += reviews.get(i).getRating();
			}
		}
		previousMonth = previousMonth / nOfPreviousMonth;
		model.addAttribute("previousMonth", Math.round(previousMonth*100.0)/100.0);
		
		
		model.addAttribute("reviews", reviews);
		
		return "statistics";
	}
	
}
