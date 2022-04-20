package com.bank.api.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.bank.api.domain.BaseRepository;
import com.bank.api.entities.Manager;
import com.bank.api.entities.QManager;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

public interface ManagerRepository extends BaseRepository<Manager>,
	QuerydslPredicateExecutor<Manager>, QuerydslBinderCustomizer<QManager> {

	@Override
	default void customize(QuerydslBindings bindings, QManager root) {
		bindings.bind(String.class)
			.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}
}
