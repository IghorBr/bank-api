package com.bank.api.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AccountActionDTO;
import com.bank.api.dtos.AccountDTO;
import com.bank.api.dtos.PaymentDTO;
import com.bank.api.entities.Account;
import com.bank.api.repositories.AccountRepository;
import com.bank.api.services.AccountService;
import com.bank.api.services.exceptions.AmountValueException;
import com.bank.api.services.exceptions.BankException;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController extends BaseController<Account, AccountDTO> {

	public AccountController() {
		super(Account.class, AccountDTO.class);
	}

	@Autowired private AccountService accountService;
	
	private static final String VALUE_ERROR = "Value will become lower than zero";

	@GetMapping(value = "/search")
	public ResponseEntity<List<AccountDTO>> search(
		@QuerydslPredicate(root = Account.class, bindings = AccountRepository.class) Predicate predicate) {

		List<AccountDTO> dtos = mapList(accountService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
	
	@PutMapping(value = "/withdraw")
	public ResponseEntity<Void> withdraw(@RequestBody @Valid AccountActionDTO accountAct) throws BankException {
		if (accountAct.getAmount().compareTo(BigDecimal.ZERO) > 0 || accountAct.getAmount().compareTo(BigDecimal.ZERO) == 0)
			throw new AmountValueException(VALUE_ERROR);
		
		accountService.withdraw(accountAct);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/deposit")
	public ResponseEntity<Void> deposit(@RequestBody @Valid AccountActionDTO accountAct) throws BankException { 
		if (accountAct.getAmount().compareTo(BigDecimal.ZERO) > 0 || accountAct.getAmount().compareTo(BigDecimal.ZERO) == 0)
			throw new AmountValueException(VALUE_ERROR);
		
		accountService.deposit(accountAct);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/transfer")
	public ResponseEntity<Void> transfer(@RequestBody @Valid AccountActionDTO accountAct) throws BankException {
		if (Objects.isNull(accountAct.getAccountNumber()) || Objects.isNull(accountAct.getAgencyTransferNumber()))
			throw new BankException("Account number and Agency number cannot be empty");
			
		if (accountAct.getAmount().compareTo(BigDecimal.ZERO) > 0 || accountAct.getAmount().compareTo(BigDecimal.ZERO) == 0)
			throw new AmountValueException(VALUE_ERROR);
		
		accountService.transfer(accountAct);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/verify-acc")
	public ResponseEntity<Boolean> verifyUserAccount(@RequestBody PaymentDTO dto) throws BankException {
		return ResponseEntity.ok(accountService.verifyUserAccount(dto));
	}
}
