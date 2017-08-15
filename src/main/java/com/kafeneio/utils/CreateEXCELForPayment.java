package com.kafeneio.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kafeneio.DTO.PaymentDto;
import com.kafeneio.model.PaymentModel;
import com.kafeneio.repository.PaymentDetailRepository;
@Component
public class CreateEXCELForPayment{
	
	@Inject
	PaymentDetailRepository paymentRepository;
	public void buildPaymentDetailTbl(List<PaymentDto> list)  {
		
	for(int i=0;i<list.size();i++){
		PaymentModel paymentModel=new PaymentModel();
		BeanUtils.copyProperties(list.get(i),paymentModel);
		paymentRepository.save(paymentModel);
	}	
	}
}

