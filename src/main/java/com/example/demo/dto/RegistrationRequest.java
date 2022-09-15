package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegistrationRequest {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String password;
}
