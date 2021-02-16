package com.rohit.ss.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rohit.ss.entity.User;
import com.rohit.ss.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JPAUserDetailsService implements UserDetailsService {

	
	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {

		User user = userRepository.findByUsername(username)
			.orElseThrow(()->new BadCredentialsException("invalid username or password"));
		
		return new SecurityUser(user);
	}

}
