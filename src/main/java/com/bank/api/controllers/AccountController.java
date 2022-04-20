package com.bank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AccountDTO;
import com.bank.api.entities.Account;
import com.bank.api.services.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController extends BaseController<Account, AccountDTO> {

	public AccountController() {
		super(Account.class, AccountDTO.class);
	}
	
	@Autowired private AccountService accountService;
	
}
