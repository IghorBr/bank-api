package com.bank.api.services;

import java.util.List;

import javax.validation.Valid;

import com.bank.api.domain.BaseService;
import com.bank.api.dtos.UserCreateDTO;
import com.bank.api.entities.Manager;
import com.bank.api.entities.User;
import com.bank.api.services.exceptions.BankException;
import com.querydsl.core.types.Predicate;

public interface ManagerService extends BaseService<Manager> {

	List<Manager> search(Predicate predicate);

	User createAccount(@Valid UserCreateDTO userCreate) throws BankException;

}
