package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ConfirmTokenRepository;
import com.example.demo.model.ConfirmToken;

@Service
public class ConfirmTokenService {

	@Autowired
	ConfirmTokenRepository confirmTokenRepository;

	public void saveConfirmToken(ConfirmToken token) {
		confirmTokenRepository.save(token);
	}

	public Optional<ConfirmToken> getToken(String token) {
		return confirmTokenRepository.findByToken(token);
	}

	public void setConfirmedAt(String token) {
		confirmTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
