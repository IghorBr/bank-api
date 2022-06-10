package com.bank.api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.api.configs.security.UserDetailsService;
import com.bank.api.configs.security.UserSecurity;
import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.dtos.AccountActionDTO;
import com.bank.api.dtos.PaymentDTO;
import com.bank.api.entities.Account;
import com.bank.api.entities.BankStatement;
import com.bank.api.entities.User;
import com.bank.api.entities.enums.BankAction;
import com.bank.api.entities.enums.UserType;
import com.bank.api.repositories.AccountRepository;
import com.bank.api.services.AccountService;
import com.bank.api.services.AgencyService;
import com.bank.api.services.UserService;
import com.bank.api.services.exceptions.AmountValueException;
import com.bank.api.services.exceptions.BankException;
import com.bank.api.services.exceptions.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	@Autowired private AccountRepository accountRepository;
	@Autowired private UserService userService;
	@Autowired private AgencyService agencyService;
	
	private static final String USER_NOT_FOUND = "User not Found";
	private static final String ACTION_NOT_ALLOWED = "You cannot do this action";
	
	@Override
	public Account save(Account account) {
		BankStatement bs = new BankStatement(BankAction.CREATE, account, null, account.getBalance(), null);
		account.addHistory(bs);
		return super.save(account);
	}

	@Override
	public List<Account> saveAll(List<Account> entities) {
		for (Account account : entities) {
			BankStatement bs = new BankStatement(BankAction.CREATE, account, null, account.getBalance(), null);
			account.addHistory(bs);
		}
		
		return super.saveAll(entities);
	}
	
	@Override
	public List<Account> search(Predicate predicate) {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll(predicate).forEach(accounts::add);
		
		return accounts;
	}

	@Override
	@Transactional
	public void withdraw(AccountActionDTO accountAct) throws BankException {
		UserSecurity loggedUser = UserDetailsService.getLoggedUser();
		
		if (loggedUser.getUserType() == UserType.ADMIN)
			throw new BankException(ACTION_NOT_ALLOWED);
		
		
		User user = userService.findById(loggedUser.getId()).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND));
		this.verifyPassword(accountAct.getPassword(), user.getAccount());
		
		BigDecimal amount = accountAct.getAmount();
		BigDecimal newBalance = user.getAccount().getBalance().subtract(amount);
		
		BankStatement bs = new BankStatement(BankAction.WITHDRAW, user.getAccount(), null, amount, null);
		
		if (newBalance.compareTo(BigDecimal.ZERO) > 0) {
			throw new AmountValueException("Your balance will become lower than zero"); 
		}
		
		user.getAccount().addHistory(bs);
		user.getAccount().setBalance(newBalance);
		super.save(user.getAccount());
	}

	@Override
	@Transactional
	public void deposit(AccountActionDTO accountAct) throws BankException {
		UserSecurity loggedUser = UserDetailsService.getLoggedUser();
		
		if (loggedUser.getUserType() == UserType.ADMIN)
			throw new BankException(ACTION_NOT_ALLOWED);
		
		User user = userService.findById(loggedUser.getId()).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND));
		this.verifyPassword(accountAct.getPassword(), user.getAccount());
		
		BigDecimal amount = accountAct.getAmount();
		BigDecimal newBalance = user.getAccount().getBalance().add(amount);
		
		BankStatement bs = new BankStatement(BankAction.DEPOSIT, user.getAccount(), null, amount, null);
		
		user.getAccount().addHistory(bs);
		user.getAccount().setBalance(newBalance);
		super.save(user.getAccount());
	}

	@Override
	@Transactional
	public void transfer(AccountActionDTO accountAct) throws BankException {
		UserSecurity loggedUser = UserDetailsService.getLoggedUser();
		
		if (loggedUser.getUserType() == UserType.ADMIN)
			throw new BankException(ACTION_NOT_ALLOWED);
		
		User senderUser = userService.findById(loggedUser.getId()).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND));
		this.verifyPassword(accountAct.getPassword(), senderUser.getAccount());
		
		Account receiverAccount = agencyService
								.findAccountByAgencyAndAccNumber(accountAct.getAgencyTransferNumber(), accountAct.getAccountNumber())
								.orElseThrow(() -> new ObjectNotFoundException("Receiver account not found"));
		
		User receiverUser = userService.findUserByAccount(receiverAccount);
		if (!receiverAccount.getEnabled().booleanValue())
			throw new BankException("User not enabled");
		
		BigDecimal newBalanceSender = senderUser.getAccount().getBalance().subtract(accountAct.getAmount());
		
		BankStatement bsPayer = new BankStatement(BankAction.TRANSFER, senderUser.getAccount(), receiverAccount, accountAct.getAmount(), null);
		
		if (newBalanceSender.compareTo(BigDecimal.ZERO) > 0) {
			throw new AmountValueException("Your balance will become lower than zero");
		}
		
		BigDecimal newBalanceReceiver = receiverUser.getAccount().getBalance().add(accountAct.getAmount());
		
		senderUser.getAccount().addHistory(bsPayer);
		senderUser.getAccount().setBalance(newBalanceSender);
		
		receiverUser.getAccount().setBalance(newBalanceReceiver);
		
		super.saveAll(Arrays.asList(senderUser.getAccount(), receiverUser.getAccount()));
	}
	
	private void verifyPassword(String password, Account account) throws BankException {
		if (!password.equals(account.getPassword())) 
			throw new BankException("Wrong Account Password");
	}

	@Override
	public Boolean verifyUserAccount(PaymentDTO dto) throws BankException {
		User user = userService.findByEmail(dto.getEmail());
		
		if (user == null)
			throw new ObjectNotFoundException(USER_NOT_FOUND);
		
		BigDecimal balance = user.getAccount().getBalance();
		
		boolean isAllowed = balance.doubleValue() >= dto.getValue();
		
		if (isAllowed) {
			user.getAccount().setBalance(BigDecimal.valueOf(balance.doubleValue() - dto.getValue()));
			BankStatement bs = new BankStatement(BankAction.WITHDRAW, user.getAccount(), null, BigDecimal.valueOf(dto.getValue()), null);
			
			user.getAccount().addHistory(bs);
			
			this.update(user.getAccount());
			return isAllowed;
		}
		else {
			return isAllowed;
		}
	}
}
