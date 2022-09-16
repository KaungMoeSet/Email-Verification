package com.example.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.service.impl.RegistrationServiceImpl;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin
public class RegisterationController {

	@Autowired
	private RegistrationServiceImpl registrationServiceImpl;

	@PostMapping
	String register(@RequestBody RegistrationRequest regRequest) {
		return registrationServiceImpl.userRegister(regRequest);
	}

	@PutMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationServiceImpl.confirmToken(token);
	}
}
