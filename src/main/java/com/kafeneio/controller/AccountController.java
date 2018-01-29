package com.kafeneio.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.AccountDTO;
import com.kafeneio.service.AccountService;
import com.kafeneio.enums.ResponseKeyName;

@RestController
public class AccountController extends BaseRestController {
	@Inject
	AccountService accountService;
	@RequestMapping(value = "/saveAccountDetailsOfUser", method = RequestMethod.POST)
	public ResponseEntity<Object> saveAccountDetailsOfUser(@RequestBody  AccountDTO accountDto){
		accountDto=accountService.saveAccountDetailsOfUser(accountDto);
		return  ResponseEntity.ok(createSuccessResponse(ResponseKeyName.ACCOUNT_DETAILS,accountDto));
	}
	
	
	@RequestMapping(value = "/getAccountDetails", method = RequestMethod.GET)
	public ResponseEntity<Object> getAccountDetailsOfUser(@RequestParam (value="user_id",required=true)Long user_id){
		AccountDTO accountDto=accountService.getUserAccountDetails(user_id);
		return  ResponseEntity.ok(createSuccessResponse(ResponseKeyName.ACCOUNT_DETAILS,accountDto));
	}
}
