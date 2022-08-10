package com.kate.zavrsni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kate.zavrsni.model.User;
import com.kate.zavrsni.model.Wine;
import com.kate.zavrsni.repository.UserRepository;
import com.kate.zavrsni.repository.WineRepository;

@Service
public class WineService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WineRepository wineRepository;
	
	public User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserEmail = authentication.getName();
		    User currentUser = userRepository.findByEmail(currentUserEmail);
		    return currentUser;
		}
		return null;
	}

	public Wine findById(int id) {
		return wineRepository.findById(id);
	}
}
