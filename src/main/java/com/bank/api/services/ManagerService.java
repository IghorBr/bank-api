package com.bank.api.services;

import com.bank.api.entities.Manager;
import com.querydsl.core.types.Predicate;

import java.util.List;

import com.bank.api.domain.BaseService;

public interface ManagerService extends BaseService<Manager> {

	List<Manager> search(Predicate predicate);

}
