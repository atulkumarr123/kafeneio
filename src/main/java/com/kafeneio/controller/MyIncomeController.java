package com.kafeneio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.IncomeDetailsDTO;
import com.kafeneio.service.MyIncomeService;
import com.kafeneio.enums.ResponseKeyName;

@RestController
public class MyIncomeController extends BaseRestController{
    @Autowired
    MyIncomeService myIncomeService;
	@RequestMapping(value="/getBinaryIncome", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>getBinaryIncome(@RequestParam(value = "Id", required = true) Long Id,@RequestParam(value="status", required=true)Boolean status,@RequestParam(value="incomeType")String incomeType){
		
	             IncomeDetailsDTO incomeDetails=	myIncomeService.getIncome(Id, status, incomeType);
		      return ResponseEntity.ok(createSuccessResponse(ResponseKeyName.INCOME_DETAILS, incomeDetails));
	}
}
