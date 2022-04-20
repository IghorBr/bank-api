package com.bank.api.services;

import java.util.List;

import com.bank.api.domain.BaseService;
import com.bank.api.entities.User;
import com.querydsl.core.types.Predicate;

public interface UserService extends BaseService<User> {

	List<User> search(Predicate predicate);
}
