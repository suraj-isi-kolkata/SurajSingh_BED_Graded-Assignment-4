package com.greatlearning.employeemgmtsystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.employeemgmtsystem.model.User;
import com.greatlearning.employeemgmtsystem.repository.UserRepository;
import com.greatlearning.employeemgmtsystem.security.config.SecurityDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		return new SecurityDetails(user);
	}

}
