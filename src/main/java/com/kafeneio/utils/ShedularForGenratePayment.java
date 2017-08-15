package com.kafeneio.utils;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kafeneio.DTO.PaymentDto;
import com.kafeneio.service.MyIncomeService;

@Component
public class ShedularForGenratePayment {
	@Inject
	MyIncomeService myIncomeService;
	@Inject
	CreateEXCELForPayment createXls;
	@Scheduled(cron="0 00 12 * * TUE")
 public void ShedularForCreateExclForPayment() throws Exception{
		System.out.println("hi===++>>>>>>>>>>>>>>>>>>>>.");
		List<PaymentDto> paymentDtoList=myIncomeService.getPaymentDetails();
		
		createXls.buildPaymentDetailTbl(paymentDtoList);
	}
}
