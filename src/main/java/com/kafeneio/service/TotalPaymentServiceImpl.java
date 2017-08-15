package com.kafeneio.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.PaymentDto;
import com.kafeneio.model.AccountModel;
import com.kafeneio.model.PaymentModel;
import com.kafeneio.repository.AccountRepository;
import com.kafeneio.repository.PaymentDetailRepository;
@Service
public class TotalPaymentServiceImpl implements TotalPaymentService {
     @Inject
     PaymentDetailRepository paymentDetailsRepository;
     @Inject
     AccountRepository accountRepository;
	@Override
	public List<PaymentDto> getAllUserDetailsForPayment(Boolean status) {
		List<PaymentModel> paymentModelList=paymentDetailsRepository.findByStatus(status);
		List<PaymentDto>paymentDtoList=new ArrayList<>();
		for(int i=0;i<paymentModelList.size();i++){
			PaymentDto paymentDto=new PaymentDto();
			Long id=paymentModelList.get(i).getUser_id();
			AccountModel accountModel=accountRepository.findByUserId(id);
			BeanUtils.copyProperties(paymentModelList.get(i),paymentDto);
			paymentDto.setAccountNumber(accountModel.getAccountNumber());
			paymentDto.setBankName(accountModel.getBankName());
			paymentDto.setIfscCode(accountModel.getIfscCode());
			paymentDtoList.add(paymentDto);
			
			}
		return paymentDtoList;
	}

}
