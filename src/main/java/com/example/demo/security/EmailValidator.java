package com.example.demo.security;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements Predicate<String> {

	private final String mailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private final Pattern pattern = Pattern.compile(mailRegex);
	private Matcher matcher;
	
	@Override
	public boolean test(String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
