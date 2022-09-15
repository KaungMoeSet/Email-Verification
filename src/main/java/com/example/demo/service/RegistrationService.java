package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppUser;
import com.example.demo.model.AppUserRole;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.security.EmailValidator;

@Service
public class RegistrationService {

	@Autowired
	AppUserService appUserService;
	
	@Autowired
	private EmailValidator emailValidator;
	
	public AppUser userRegister(RegistrationRequest regRequest) {
		Boolean isValidEmail = emailValidator.test(regRequest.getEmail());
		if( !isValidEmail ) {
			throw new IllegalStateException("Email is not Valid");
		}
		
		return appUserService.signUpUser(
				new AppUser( regRequest.getFirstName(), 
							 regRequest.getLastName(), 
							 regRequest.getEmail(),
							 regRequest.getPassword(),
							 AppUserRole.USER));
	}


}
