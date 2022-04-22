package com.bank.api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.api.configs.security.UserDetailsService;
import com.bank.api.configs.security.UserSecurity;
import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.dtos.UserCreateDTO;
import com.bank.api.entities.Account;
import com.bank.api.entities.Manager;
import com.bank.api.entities.User;
import com.bank.api.entities.enums.UserType;
import com.bank.api.repositories.ManagerRepository;
import com.bank.api.services.AccountService;
import com.bank.api.services.ManagerService;
import com.bank.api.services.UserService;
import com.querydsl.core.types.Predicate;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	@Autowired ManagerRepository managerRepository;
	private ModelMapper mapper;
	
	@Autowired UserService userService;
	@Autowired AccountService accountService;
	
	@Override
	public List<Manager> search(Predicate predicate) {
		List<Manager> managers = new ArrayList<Manager>();
		managerRepository.findAll(predicate).forEach(managers::add);
		
		return managers;
	}

	@Override
	@Transactional(readOnly = false)
	public User createAccount(@Valid UserCreateDTO userCreate) throws Exception {
		UserSecurity loggedUser = UserDetailsService.getLoggedUser();
		
		if (loggedUser.getUserType() == UserType.USER) {
			throw new Exception();
		}
		
		mapper = new ModelMapper();
		
		User newUser = mapper.map(userCreate, User.class);
		newUser.setUserType(UserType.USER);
		
		newUser = userService.save(newUser);
		
		BigDecimal value = Objects.nonNull(userCreate.getBalance()) ? userCreate.getBalance() : new BigDecimal(0);
		Account newAcc = new Account(value, userCreate.getPasswordAccount(), loggedUser.getAccount().getAgency());
		
		newAcc = accountService.save(newAcc);
		newUser.setAccount(newAcc);
		
		newUser = userService.save(newUser);
		return newUser;
	}
}
