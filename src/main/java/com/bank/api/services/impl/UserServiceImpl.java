package com.bank.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.User;
import com.bank.api.repositories.UserRepository;
import com.bank.api.services.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired private UserRepository userRepository;
}
