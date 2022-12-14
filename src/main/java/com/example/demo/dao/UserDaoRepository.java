package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppUser;

@Repository
public interface UserDaoRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByEmail(String email);

	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query("update AppUser a set a.enabled = true where a.email = :email")
	void enableAppUser(@Param("email")String email);
}
