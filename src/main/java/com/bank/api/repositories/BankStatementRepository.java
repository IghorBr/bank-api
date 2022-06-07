package com.bank.api.repositories;	

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.bank.api.domain.BaseRepository;
import com.bank.api.entities.Account;
import com.bank.api.entities.BankStatement;
import com.bank.api.entities.QBankStatement;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

public interface BankStatementRepository extends BaseRepository<BankStatement>, 
	QuerydslPredicateExecutor<BankStatement>, QuerydslBinderCustomizer<QBankStatement> {
	
	@Override
	default void customize(QuerydslBindings bindings, QBankStatement root) {
		bindings.bind(String.class)
			.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}
	
	List<BankStatement> findByPayer(Account payer);
}
