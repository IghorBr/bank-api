package com.bank.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Manager;
import com.bank.api.repositories.ManagerRepository;
import com.bank.api.services.ManagerService;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	@Autowired ManagerRepository managerRepository;
}
