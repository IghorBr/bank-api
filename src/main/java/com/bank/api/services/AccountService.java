package com.bank.api.services;

import java.util.List;

import com.bank.api.domain.BaseService;
import com.bank.api.dtos.AccountActionDTO;
import com.bank.api.entities.Account;
import com.querydsl.core.types.Predicate;

public interface AccountService extends BaseService<Account> {

	List<Account> search(Predicate predicate);
	
	void withdraw(AccountActionDTO accountAct) throws Exception;
	void deposit(AccountActionDTO accountAct) throws Exception;
	void transfer(AccountActionDTO accountAct) throws Exception;

}
