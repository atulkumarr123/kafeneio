package com.kafeneio.service;

import com.kafeneio.DTO.AccountDTO;
import com.kafeneio.model.AccountModel;

public interface AccountService {
	AccountDTO saveAccountDetailsOfUser(AccountDTO accountDTO);
	AccountDTO getUserAccountDetails(Long user_Id);
}
