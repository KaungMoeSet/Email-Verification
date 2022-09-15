package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.model.AppUser;
import com.example.demo.model.AppUserRole;
import com.example.demo.model.ConfirmToken;
import com.example.demo.security.EmailValidator;

@Service
public class RegistrationService {

	@Autowired
	AppUserService appUserService;

	@Autowired
	ConfirmTokenService confirmTokenService;

	@Autowired
	private EmailValidator emailValidator;

	public String userRegister(RegistrationRequest regRequest) {
		Boolean isValidEmail = emailValidator.test(regRequest.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("Email is not Valid");
		}

		return appUserService.signUpUser(new AppUser(regRequest.getFirstName(), regRequest.getLastName(),
				regRequest.getEmail(), regRequest.getPassword(), AppUserRole.USER));
	}

	@Transactional
	public String confirmToken(String token) {

		ConfirmToken confirmToken = confirmTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("token not found"));

		if (confirmToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}
		
		LocalDateTime expiredAt = confirmToken.getExpireAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmTokenService.setConfirmedAt(token);
		
		appUserService.enableAppUser(confirmToken.getAppUser().getEmail());
		
		return "confirmed";
	}
}
