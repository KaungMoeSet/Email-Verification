package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ConfirmToken;

@Repository
public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {

	Optional<ConfirmToken> findByToken(String token);

	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("update ConfirmToken c set c.confirmedAt = :now where c.token = :token")
	void updateConfirmedAt(@Param("token")String token, @Param("now")LocalDateTime now);
}
