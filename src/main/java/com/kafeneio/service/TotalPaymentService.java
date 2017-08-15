package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.PaymentDto;

public interface TotalPaymentService {
List<PaymentDto> getAllUserDetailsForPayment(Boolean status);
}
