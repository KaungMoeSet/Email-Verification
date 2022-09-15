package com.example.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AppUser;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.RegistrationService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RegisterationController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/register")
	AppUser register(@RequestBody RegistrationRequest regRequest) {
		return registrationService.userRegister(regRequest);
	}
}
