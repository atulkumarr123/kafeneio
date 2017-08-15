package com.kafeneio.repository;

import java.io.Serializable;
import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.RegistrationModel;


public interface RegistrationRepository extends  JpaRepository<RegistrationModel, Long> {

	   @Query("SELECT r FROM RegistrationModel r WHERE r.loginModel.email=:email")
	    RegistrationModel findOneByEmail(@Param("email") String email);
	     RegistrationModel findBySponsorId(@Param("sponsorId") String sponsorId);
	     RegistrationModel findById(@Param("id") Long id);
	     
	   @Modifying 
	   @Query("UPDATE RegistrationModel r SET r.profileImage = :profileImage WHERE r.id = :Id")
	    int  saveProfileImg(@Param("Id") Long Id, @Param("profileImage") Blob profileImage);
}
