package com.bank.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.bank.api.domain.BaseRepository;
import com.bank.api.entities.Account;
import com.bank.api.entities.Agency;
import com.bank.api.entities.QAgency;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface AgencyRepository extends BaseRepository<Agency>, 
	QuerydslPredicateExecutor<Agency>, QuerydslBinderCustomizer<QAgency>{

	@Override
	default void customize(QuerydslBindings bindings, QAgency root) {
		bindings.bind(String.class)
			.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}
	
	@Query(value = "SELECT acc FROM Agency agc INNER JOIN agc.accounts acc WHERE agc.agencyNumber = ?1 AND acc.accountNumber = ?2")
	Optional<Account> findAccountByAgencyAndAccNumber(String agency, String account);   
}
