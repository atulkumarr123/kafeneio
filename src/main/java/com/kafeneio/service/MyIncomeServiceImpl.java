package com.kafeneio.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.IncomeDTO;
import com.kafeneio.DTO.IncomeDetailsDTO;
import com.kafeneio.DTO.PaymentDto;
import com.kafeneio.model.AccountModel;
import com.kafeneio.model.IncomeModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.model.WorkAssignmentModel;
import com.kafeneio.repository.AccountRepository;
import com.kafeneio.repository.IncomeRepository;
import com.kafeneio.repository.RegistrationRepository;
import com.kafeneio.repository.WorkAssignmentRepository;
import com.kafeneio.enums.AppConstant;
@Service
public class MyIncomeServiceImpl implements MyIncomeService {
	@Inject
	IncomeRepository incomeRepository;
	@Inject
	RegistrationRepository registrationRepository;
	@Inject
	AccountRepository accountRepository;
	@Inject
	WorkAssignmentRepository workAssignmentRepository;
	public IncomeDetailsDTO getIncome(Long refferalId,Boolean status,String incomeType){
		 IncomeDetailsDTO incomeDetailsDto=new IncomeDetailsDTO();
		 Long leftCount=0L;
	     Long rightNodeCount=0L;
	   
	     Long compleateBinary=0l;
	     List<IncomeModel> list=incomeRepository.findByReferralIdAndStatusAndIncomeType(refferalId, status, incomeType);
	   	     if(AppConstant.INCOME_TYPE_BINARY.getStringValue().equals(incomeType)){
	    	 for(int i=0; i<list.size();i++) {
	    		 if(AppConstant.NODE_TYPE_LEFT.getStringValue().equals(list.get(i).getNode())){
	    			 leftCount=leftCount+1;
	    		 }
	    		 if(AppConstant.NODE_TYPE_RIGHT.getStringValue().equals(list.get(i).getNode())){
	    			 rightNodeCount=rightNodeCount+1;
	    		 } 
	    	 }
	    	 if(leftCount<=rightNodeCount){
	    		 compleateBinary=leftCount;
	    	 }
	    	 else{
	    		 compleateBinary=rightNodeCount;
	    	 }
	    	 incomeDetailsDto.setLeftNodeCount(leftCount);
	    	 incomeDetailsDto.setRightNodeCount(rightNodeCount);
	    	 incomeDetailsDto.setAmmount(compleateBinary*AppConstant.INCOME_TYPE_BINARY_AMMOUNT.getValue());
	    	 incomeDetailsDto.setChildNode(compleateBinary);
	    	 
	     }
	       else{
	    	 incomeDetailsDto.setChildNode((long)list.size());
    		 incomeDetailsDto.setAmmount((long)(list.size())*(AppConstant.INCOME_TYPE_DI_AMMOUNT.getValue()));
	     }
		return incomeDetailsDto;
	}
	
	
public  List<PaymentDto> getPaymentDetails(){
	
	List<PaymentDto>paymentDtoLists=new ArrayList<>();
	int compleateBinery;
	List<RegistrationModel>list=registrationRepository.findAll();
	
	for(int i=0; i<list.size();i++){
		PaymentDto paymentDTO=new PaymentDto();
		AccountModel accountModel=accountRepository.findByUserId(list.get(i).getId());
		if(accountModel!=null)
			
		{
		paymentDTO.setAccountNumber(accountModel.getAccountNumber());
		paymentDTO.setBankName(accountModel.getBankName());
		paymentDTO.setIfscCode(accountModel.getAccountHolderName());
		paymentDTO.setUser_id(list.get(i).getId());
		paymentDTO.setUserName(list.get(i).getName());
		
		
		List<IncomeModel> incomeModelForDirectIncome=incomeRepository.findByStatusAndIncomeTypeAndReferralId(false, AppConstant.INCOME_TYPE_DI.getStringValue(),list.get(i).getId());
		paymentDTO.setDirectReferralIncome(incomeModelForDirectIncome.size()*AppConstant.INCOME_TYPE_DI_AMMOUNT.getValue());
		
		List<IncomeModel> incomeModelBinaryIncomeLeftNode=incomeRepository.findByStatusAndIncomeTypeAndNode(false, AppConstant.INCOME_TYPE_BINARY.getStringValue(),list.get(i).getId(),AppConstant.NODE_TYPE_LEFT.getStringValue());
		List<IncomeModel> incomeModelBinaryIncomeRightNode=incomeRepository.findByStatusAndIncomeTypeAndNode(false, AppConstant.INCOME_TYPE_BINARY.getStringValue(),list.get(i).getId(),AppConstant.NODE_TYPE_RIGHT.getStringValue());
		if(incomeModelBinaryIncomeLeftNode.size()<incomeModelBinaryIncomeRightNode.size()){
			compleateBinery=incomeModelBinaryIncomeLeftNode.size();
		}
		else{
			compleateBinery=incomeModelBinaryIncomeRightNode.size();
		
		}
		paymentDTO.setCompleteBinary(compleateBinery);
		paymentDTO.setBinaryIncome(compleateBinery*AppConstant.INCOME_TYPE_BINARY_AMMOUNT.getValue());
		List<WorkAssignmentModel> workDone=workAssignmentRepository.findByStatus(true);
		paymentDTO.setWorkAssigmentIncome(workDone.size()*AppConstant.INCOME_TYPE_WORK_AMMOUNT.getValue());
		float totalAmount=incomeModelForDirectIncome.size()*AppConstant.INCOME_TYPE_DI_AMMOUNT.getValue()
				           +compleateBinery*AppConstant.INCOME_TYPE_BINARY_AMMOUNT.getValue()
		                   +workDone.size()*AppConstant.INCOME_TYPE_WORK_AMMOUNT.getValue();
		paymentDTO.setTotalIncome(totalAmount);
		paymentDTO.setAdminTax((totalAmount*AppConstant.ADMIN_TAX.getValue())/100);
		paymentDTO.setTds((totalAmount*AppConstant.TDS_TAX.getValue())/100);
		paymentDTO.setTotalPayoutIncome(totalAmount-((totalAmount*AppConstant.ADMIN_TAX.getValue())/100+
				(totalAmount*AppConstant.TDS_TAX.getValue())/100));
		paymentDTO.setStatus(false);
		paymentDtoLists.add(paymentDTO);
		}
	}
	
	
	
	return paymentDtoLists;
}
}
