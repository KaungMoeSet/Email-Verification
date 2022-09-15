package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "confirmation")
public class ConfirmToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable = false)
	private String token;

	@Column(nullable = false,
			name = "createdAt")
	private LocalDateTime createdAt;

	@Column(nullable = false,
			name = "expireAt")
	private LocalDateTime expireAt;

	@Column(nullable = false,
			name = "confirmedAt")
	private LocalDateTime confirmedAt;

	@ToString.Exclude
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="user_id")
	AppUser appUser;

	public ConfirmToken(String token, LocalDateTime createdAt, LocalDateTime expireAt, AppUser appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
		this.appUser = appUser;
	}
	
}
