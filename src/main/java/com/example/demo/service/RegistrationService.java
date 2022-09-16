package com.example.demo.service;

import com.example.demo.dto.RegistrationRequest;

public interface RegistrationService {

	String userRegister(RegistrationRequest regRequest);
	String confirmToken(String token);
}
