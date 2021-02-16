package com.rohit.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager uds=new InMemoryUserDetailsManager();
		
		UserDetails u1 = User.builder().username("rohit")
				.password("12345")
				.authorities("read")
				.build();
		
		UserDetails u2 = User.builder().username("rahul")
				.password("12345")
				.authorities("read")
				.build();
		uds.createUser(u1);
		uds.createUser(u2);
		
		return uds;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
