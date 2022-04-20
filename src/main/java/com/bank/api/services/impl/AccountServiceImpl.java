package com.bank.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Account;
import com.bank.api.repositories.AccountRepository;
import com.bank.api.services.AccountService;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	@Autowired private AccountRepository acountRepository;
}
