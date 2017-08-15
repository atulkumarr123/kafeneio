package com.kafeneio.utils;

import java.sql.Date;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.model.IncomeModel;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.repository.IncomeRepository;
import com.kafeneio.repository.ParentChildRepository;
import com.kafeneio.repository.RegistrationRepository;
import com.kafeneio.enums.AppConstant;

@Component
public class ParentIdCalculate {
	@Inject
	ParentChildRepository parentChildRepository;
    @Inject
    IncomeRepository incomeRepository;
    @Inject 
    RegistrationRepository registrationRepository;
	public Long returnParentId(ParentChildDTO parentChildDto) {
		
		Long parentId;
		ParentChildDTO parentchildDto1 = null;
		ParentChildModel parentModel = parentChildRepository.findByparentIdAndPosition(parentChildDto.getParentId(),parentChildDto.getPosition());
		if (parentModel != null && parentModel.getId() != null) {
			parentchildDto1 = new ParentChildDTO();
			BeanUtils.copyProperties(parentModel, parentchildDto1);
			parentchildDto1.setParentId(parentModel.getRegistration().getId());
			return returnParentId(parentchildDto1);
		}
		else {
			parentId = parentChildDto.getParentId();
			return parentId;
		}

	}
	/*
	 * This method is used for create binary income.
	 * node and user id input variable is not update
	 * Child Id and reffralId is update 
	 * */
	public void generateBinaryIncome(Long childId,String reffaralId,String node,Long userId) {
		String date=AppUtils.createDate();
		RegistrationModel registrationModel = null;
		ParentChildModel parentChildModel=parentChildRepository.findByRegistrationId(childId);
		if(parentChildModel == null)
		{
			return;
		}
		else
		{
		 registrationModel=registrationRepository.findById(parentChildModel.getParentId());
		}
		
		if(parentChildModel.getParentId()==0)
		{
			return;
			
		}
		else if(reffaralId.equals(registrationModel.getSponsorId())) {
			generateBinaryIncome(parentChildModel.getParentId(), reffaralId, parentChildModel.getPosition(),userId);
		}
		
		
		else
		{
			IncomeModel incomeModel=new IncomeModel();
			incomeModel.setUserId(userId);
			incomeModel.setReferralId(parentChildModel.getParentId());
			incomeModel.setDate(date);
			incomeModel.setStatus(false);
			incomeModel.setNode(parentChildModel.getPosition());
			incomeModel.setIncomeType(AppConstant.INCOME_TYPE_BINARY.getStringValue());
			incomeRepository.save(incomeModel);
			
			generateBinaryIncome(parentChildModel.getParentId(), reffaralId, node, userId);
			
		}
	}
	

}
