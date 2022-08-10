package com.kate.zavrsni.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

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

import com.kate.zavrsni.dto.WineDto;
import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.UserRepository;
import com.kate.zavrsni.repository.WineRepository;
import com.kate.zavrsni.service.WineService;

@Controller
@RequestMapping("/wine")
public class WineController {

	@Autowired
	private WineService wineService;
	
	@Autowired 
	private WineRepository wineRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/new")
	public String newWine(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Wine wine = new Wine();
		model.addAttribute("wine", wine);
		return "newWine";
	}
	
	@GetMapping("/edit/{id}")
	public String editWine(@PathVariable Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Wine wine = wineRepository.getReferenceById(id);
		wine.setId(id);
		model.addAttribute("wine", wine);
		
		return "newWine";
	}
	
	@PostMapping("/save")
	public String saveWine(@ModelAttribute("wine") Wine wine, 
			BindingResult result, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		if (result.hasErrors()) {
			return "newWine";
		}
		
		Wine saveWine;
		
		if (wineRepository.existsById(wine.getId()))
			saveWine = wineRepository.findById(wine.getId());
		else 
			saveWine = new Wine();
		
		saveWine.setOwner(currentUser);
		saveWine.setName(wine.getName());
		saveWine.setAcidity(wine.getAcidity());
		saveWine.setAlcohol(wine.getAlcohol());
		saveWine.setBody(wine.getBody());
		//saveWine.setFoodPairing(wine.getFoodPairing());
		saveWine.setFoodPairing("janje");
		//saveWine.setNote(wine.getNote());
		saveWine.setNote("blackberry");
		saveWine.setPosition(wine.getPosition());
		saveWine.setRegion(wine.getRegion());
		saveWine.setSweetness(wine.getSweetness());
		saveWine.setTannin(wine.getTannin());
		saveWine.setType(wine.getType());
		saveWine.setVariety(wine.getVariety());
		saveWine.setYear(wine.getYear());
		saveWine.setPicture(wine.getPicture());
		
		wineRepository.save(saveWine);
			
		model.addAttribute("message", "Your wine has been saved. ");
		return "redirect:/profile";
	}
	
	@GetMapping("/{id}")
	public String details(@PathVariable Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Wine wine = wineRepository.getReferenceById(id);
		model.addAttribute("wine", wine);
		if (currentUser.getId() == wine.getOwner().getId())
			model.addAttribute("profil", true);
		else
			model.addAttribute("profil", false);
		
		return "wineDetails";
	}
	
	@GetMapping("/delete/{id}") 
	public String delete(@PathVariable Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Wine wine = wineRepository.getReferenceById(id);
		model.addAttribute("wine", wine);
		
		return "areYouSure";
	}
	
	@GetMapping("/proceed")
	public String proceed(@RequestParam (value = "wineId") Integer id, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		Wine wine = wineRepository.getReferenceById(id);
		wineRepository.delete(wine);
		
		return "redirect:/profile";
	}
	
	@GetMapping
	public String allWines(Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		List<Wine> wines = wineRepository.findAll();
		
		if (wines.isEmpty())
			model.addAttribute("empty", false);
		
		Collections.reverse(wines);
		model.addAttribute("wines", wines);	
		
		List<User> winery = userRepository.findAll();
		model.addAttribute("winery", winery);
		
		List<String> variety = new ArrayList<String>();
		List<Integer> year = new ArrayList<Integer>();
		
		for (int i = 0; i < wines.size(); i++) {
			if (variety.contains(wines.get(i).getVariety()) == false)
				variety.add(wines.get(i).getVariety());
			if (year.contains(wines.get(i).getYear()) == false)
				year.add(wines.get(i).getYear());
		}
		
		model.addAttribute("variety", variety);
		model.addAttribute("year", year);
		
		WineDto wineDto = new WineDto();
		model.addAttribute("wineDto", wineDto);
		
		return "wines";
	}
	
	@GetMapping("/filter")
	public String filter(@ModelAttribute("wineDto") WineDto wineDto, Model model) {
		User currentUser = wineService.getUser();
		model.addAttribute("user", currentUser);
		
		List<Wine> wines = wineRepository.findAll();
		List<Wine> filteredWines = new ArrayList<>();
		
		List<User> winery = userRepository.findAll();
		model.addAttribute("winery", winery);
		
		List<String> variety = new ArrayList<String>();
		List<Integer> year = new ArrayList<Integer>();
		
		for (int i = 0; i < wines.size(); i++) {
			if (variety.contains(wines.get(i).getVariety()) == false)
				variety.add(wines.get(i).getVariety());
			if (year.contains(wines.get(i).getYear()) == false)
				year.add(wines.get(i).getYear());
		}
		
		model.addAttribute("variety", variety);
		model.addAttribute("year", year);
		
		
		for (int i = 0; i < wines.size(); i++) { 
			if (wineDto.getWinery().equals(wines.get(i).getOwner().getUsername())
					&& filteredWines.contains(wines.get(i)) == false)
				filteredWines.add(wines.get(i));
			
			if (wineDto.getVariety().equals(wines.get(i).getVariety())
					&& !filteredWines.contains(wines.get(i)))
				filteredWines.add(wines.get(i));
			
			if (wineDto.getYear() == wines.get(i).getYear()
					&& filteredWines.contains(wines.get(i)) == false)
				filteredWines.add(wines.get(i));
			

			if (wineDto.getType().equals(wines.get(i).getType())
					&& filteredWines.contains(wines.get(i)) == false)
				filteredWines.add(wines.get(i));

			if (wineDto.getPosition().equals(wines.get(i).getPosition())
					&& filteredWines.contains(wines.get(i)) == false)
				filteredWines.add(wines.get(i));

			if (wineDto.getSweetness().equals(wines.get(i).getSweetness())
					&& filteredWines.contains(wines.get(i)) == false)
				filteredWines.add(wines.get(i));
			
		}
		
		if (filteredWines.isEmpty())
			model.addAttribute("empty", true);
		
		
		Collections.reverse(filteredWines);
		model.addAttribute("wines", filteredWines);	
		
		return "wines";
	}
	
}
