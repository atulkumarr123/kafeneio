package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.IncomeDetailsDTO;
import com.kafeneio.DTO.PaymentDto;
import com.kafeneio.model.IncomeModel;

public interface MyIncomeService {
	IncomeDetailsDTO getIncome(Long refferalId,Boolean status,String incomeType);
	List<PaymentDto> getPaymentDetails();

}
