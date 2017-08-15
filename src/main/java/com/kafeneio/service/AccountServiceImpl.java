package com.kafeneio.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.AccountDTO;
import com.kafeneio.model.AccountModel;
import com.kafeneio.repository.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {
   @Inject
    AccountRepository accountRepository;
	@Override
	public AccountDTO saveAccountDetailsOfUser(AccountDTO accountDTO) {
		
		AccountModel accountModel=new AccountModel();
		BeanUtils.copyProperties(accountDTO, accountModel);
		accountModel=accountRepository.save(accountModel);
		BeanUtils.copyProperties(accountModel, accountDTO);
		return accountDTO;
	} 

	@Override
	public AccountDTO getUserAccountDetails(Long user_Id){
		AccountDTO accountDTO = new AccountDTO();
		AccountModel accountModel=accountRepository.findByUserId(user_Id);
		BeanUtils.copyProperties(accountModel, accountDTO);
		return accountDTO;
	}
	
}
