package com.bank.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Account;
import com.bank.api.repositories.AccountRepository;
import com.bank.api.services.AccountService;
import com.querydsl.core.types.Predicate;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	@Autowired private AccountRepository acountRepository;

	@Override
	public List<Account> search(Predicate predicate) {
		List<Account> accounts = new ArrayList<Account>();
		acountRepository.findAll(predicate).forEach(accounts::add);
		
		return accounts;
	}
}
