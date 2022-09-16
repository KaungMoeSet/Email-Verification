package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface AppUserService {

	String signUpUser(AppUser user);
	void enableAppUser(String email);
}
