package com.kafeneio.service;

import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;

public interface ParentChildService {
	void saveParentChild(ParentChildDTO parentChild);
	ParentChildModel findOneByEmail(String email);

}
