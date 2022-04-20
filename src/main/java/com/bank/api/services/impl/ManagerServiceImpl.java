package com.bank.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Manager;
import com.bank.api.repositories.ManagerRepository;
import com.bank.api.services.ManagerService;
import com.querydsl.core.types.Predicate;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	@Autowired ManagerRepository managerRepository;

	@Override
	public List<Manager> search(Predicate predicate) {
		List<Manager> managers = new ArrayList<Manager>();
		managerRepository.findAll(predicate).forEach(managers::add);
		
		return managers;
	}
}
