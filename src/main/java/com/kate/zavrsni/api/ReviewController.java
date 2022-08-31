package com.kate.zavrsni.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kate.zavrsni.dto.ReviewDto;
import com.kate.zavrsni.model.Review;
import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.ReviewRepository;
import com.kate.zavrsni.repository.WineRepository;
import com.kate.zavrsni.service.ReviewService;
import com.kate.zavrsni.service.WineService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private WineService wineService;
	
	@Autowired
	private WineRepository wineRepository;
	
	@GetMapping("/{id}")
	public String newReview(@PathVariable int id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setWineId(id);
		
		model.addAttribute("reviewDto", reviewDto);
		
		return "newReview";
	}	
	
	@PostMapping("/save")
	public String saveReview(@ModelAttribute ReviewDto reviewDto, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Review review = new Review();
		
		Wine wine = wineRepository.getReferenceById(reviewDto.getWineId());
		
		review.setUser(currentUser);
		review.setWine(wine);
		review.setRating(reviewDto.getRating());
		review.setDate(LocalDate.now());
		review.setButter(reviewDto.getButter());
		review.setCigarBox(reviewDto.getCigarBox());
		review.setCoconut(reviewDto.getCoconut());
		review.setDriedFruit(reviewDto.getDriedFruit());
		review.setFloral(reviewDto.getFloral());
		review.setFruit(reviewDto.getFruit());
		review.setHerbal(reviewDto.getHerbal());
		review.setLeather(reviewDto.getLeather());
		review.setNuts(reviewDto.getNuts());
		review.setSpices(reviewDto.getSpices());
		review.setToasted(reviewDto.getToasted());
		review.setTobacco(reviewDto.getTobacco());
		review.setVanilla(reviewDto.getVanilla());
		review.setYeast(reviewDto.getYeast());
		
		reviewRepository.save(review);
		
		Double previousRating = wine.getRating();
		Double previous = 0.0;
		if (previousRating != 0.0) {
			List<Review> allReviews = new ArrayList<>(wine.getReviews());
			int nOfRating = 0;
			if (allReviews.isEmpty() == false && allReviews != null) {
				for (int i = 0; i < allReviews.size(); i++) {
					nOfRating++;
					previous += allReviews.get(i).getRating();
				}
			}
			
			Double totalR = previous/nOfRating;
			
			wine.setRating(totalR);
			
		}
		else {
			wine.setRating(reviewDto.getRating());
		}
		
		wineRepository.save(wine);
		
		return "redirect:/profile";
	}
	
	@GetMapping("/delete/{id}") 
	public String delete(@PathVariable Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Review review = reviewRepository.getReferenceById(id);
		model.addAttribute("review", review);
		model.addAttribute("w", false);
		
		return "areYouSureReview";
	}
	
	@GetMapping("/proceed")
	public String proceed(@RequestParam (value = "reviewId") Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Review review = reviewRepository.getReferenceById(id);
		reviewRepository.delete(review);
		
		return "redirect:/profile";
	}

}
