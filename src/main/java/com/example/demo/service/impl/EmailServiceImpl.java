package com.example.demo.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	private final static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private JavaMailSender mailSender;

	@Override
	@Async
	public void send(String reciver, String body) {	
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setText(body);
			helper.setTo(reciver);
			helper.setSubject("Confirm your Email");
			helper.setFrom("moeset2882000@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
		}
		
	}

}
