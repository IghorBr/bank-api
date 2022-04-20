package com.bank.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AccountDTO;
import com.bank.api.entities.Account;
import com.bank.api.repositories.AccountRepository;
import com.bank.api.services.AccountService;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController extends BaseController<Account, AccountDTO> {

	public AccountController() {
		super(Account.class, AccountDTO.class);
	}

	@Autowired private AccountService accountService;

	public ResponseEntity<List<AccountDTO>> search(
		@QuerydslPredicate(root = Account.class, bindings = AccountRepository.class) Predicate predicate) {

		List<AccountDTO> dtos = mapList(accountService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
}
