package com.kafeneio.service;

import java.sql.Blob;

import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.DTO.RegistrationDTO;
import com.kafeneio.model.IncomeModel;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;

public interface RegistrationService {
	ParentChildModel saveUser(ParentChildDTO parentChildDto);
	RegistrationModel findOneByEmail(String email);
	RegistrationDTO getSponsorName(String sponsorId);
	void saveProfileImage(Blob profileImg , Long Id);
	Long findReferralUserId(String referralId);
	IncomeModel saveDirectReferral(Long referralId, ParentChildModel parentChildModel);
	 void saveBinaryIncome(ParentChildModel parentChildMode);
}
