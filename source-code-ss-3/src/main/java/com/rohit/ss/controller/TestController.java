package com.rohit.ss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.ss.entity.User;
import com.rohit.ss.entity.UserRoles;
import com.rohit.ss.pojo.UserPojo;
import com.rohit.ss.repository.UserRepository;
import com.rohit.ss.util.CommonEnum.Role;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TestController {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@PostMapping("/user/add")
	public void createUser(@RequestBody UserPojo userPojo) {
		
		User user=new User();
		user.setPassword(passwordEncoder.encode(userPojo.getPassword()));
		user.setUsername(userPojo.getUsername());
		
		List<UserRoles> roles=new ArrayList<>();
		UserRoles userRole1 = new UserRoles();
		userRole1.setAuthority(Role.ADMIN);
		roles.add(userRole1);
		user.setAuthorities(roles);
		userRepository.save(user);
	}
	
}
