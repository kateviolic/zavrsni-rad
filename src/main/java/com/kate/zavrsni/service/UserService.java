package com.kate.zavrsni.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.kate.zavrsni.dto.UserRegistrationDto;
import com.kate.zavrsni.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

	
}
