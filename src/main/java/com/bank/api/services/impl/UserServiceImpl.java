package com.bank.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.User;
import com.bank.api.repositories.UserRepository;
import com.bank.api.services.UserService;
import com.querydsl.core.types.Predicate;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired private UserRepository userRepository;
	
	@Override
	public List<User> search(Predicate predicate) {
		List<User> users = new ArrayList<User>();
		
		userRepository.findAll(predicate).forEach(users::add);
		return users;
	}
}
