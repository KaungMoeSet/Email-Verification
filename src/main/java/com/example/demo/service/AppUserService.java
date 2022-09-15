package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDaoRepository;
import com.example.demo.model.AppUser;
import com.example.demo.model.ConfirmToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserService implements UserDetailsService {

	@Autowired
	private UserDaoRepository userDaoRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ConfirmTokenService confirmTokenService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userDaoRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User not found with email: %s", email)));
	}

	public String signUpUser(AppUser user) {

		boolean userExist = userDaoRepository.findByEmail(user.getEmail()).isPresent();
		if (userExist) {
			throw new IllegalStateException("email already taken! Try new one or Sign in");
		}
		
		String encodedPwd = bCryptPasswordEncoder.encode(user.getPassword());

		user.setPassword(encodedPwd);
		userDaoRepository.save(user);
		
		String token = UUID.randomUUID().toString();
		ConfirmToken confirmToken = new ConfirmToken( token, 
													  LocalDateTime.now(), 
													  LocalDateTime.now().plusMinutes(15), 
													  user);
		
		confirmTokenService.saveConfirmToken(confirmToken);
		
		return token;
	}

	public void enableAppUser(String email) {
		userDaoRepository.enableAppUser(email);
	}
}
