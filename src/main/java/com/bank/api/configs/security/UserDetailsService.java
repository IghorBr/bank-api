package com.bank.api.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.api.entities.User;
import com.bank.api.repositories.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(email);
		
		if (user == null) 
			throw new UsernameNotFoundException("No user with email: " + email);
		
		return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getEnabled(), user.getUserType()); 
	}

}
