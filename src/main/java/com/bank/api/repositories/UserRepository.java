package com.bank.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.bank.api.domain.BaseRepository;
import com.bank.api.entities.Account;
import com.bank.api.entities.QUser;
import com.bank.api.entities.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface UserRepository extends BaseRepository<User>,
	QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
	
	@Override
	default void customize(QuerydslBindings bindings, QUser root) {
		bindings.bind(String.class)
			.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}
	
	User findByEmail(String email);
	User findByAccount(Account account);
	
	@Query(value = "CALL COUNT_USERS", nativeQuery = true)
	Integer countUsers();
	
	@Procedure("GET_COUNT_USERS_BY_TYPE")
	Integer getCountUsersByType(String type);
}
