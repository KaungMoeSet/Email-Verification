package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ConfirmTokenRepository;
import com.example.demo.model.ConfirmToken;
import com.example.demo.service.ConfirmTokenService;

@Service
public class ConfirmTokenServiceImpl implements ConfirmTokenService {

	@Autowired
	ConfirmTokenRepository confirmTokenRepository;

	@Override
	public void saveConfirmToken(ConfirmToken token) {
		confirmTokenRepository.save(token);
	}

	@Override
	public Optional<ConfirmToken> getToken(String token) {
		return confirmTokenRepository.findByToken(token);
	}

	@Override
	public void setConfirmedAt(String token) {
		confirmTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
