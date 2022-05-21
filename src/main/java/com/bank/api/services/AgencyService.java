package com.bank.api.services;

import java.util.List;
import java.util.Optional;

import com.bank.api.domain.BaseService;
import com.bank.api.entities.Account;
import com.bank.api.entities.Agency;
import com.querydsl.core.types.Predicate;

public interface AgencyService extends BaseService<Agency> {

	List<Agency> search(Predicate predicate);
	
	Optional<Account> findAccountByAgencyAndAccNumber(String agencyNumber, String accNumber);

}
