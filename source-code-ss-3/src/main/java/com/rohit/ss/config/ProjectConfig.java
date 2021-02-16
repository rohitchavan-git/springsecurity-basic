package com.rohit.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rohit.ss.repository.UserRepository;
import com.rohit.ss.security.JPAUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	private final UserRepository userRepositoty;

	@Bean
	public UserDetailsService userDetailsService() {

		return new JPAUserDetailsService(userRepositoty);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/user/*").permitAll()
			.anyRequest()
			.authenticated();
	}
}
