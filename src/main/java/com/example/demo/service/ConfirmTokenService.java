package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.ConfirmToken;

public interface ConfirmTokenService {

	 void saveConfirmToken(ConfirmToken token);
	 Optional<ConfirmToken> getToken(String token);
	 void setConfirmedAt(String token);
}
