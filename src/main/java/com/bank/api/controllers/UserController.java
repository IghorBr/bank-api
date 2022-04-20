package com.bank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.UserDTO;
import com.bank.api.entities.User;
import com.bank.api.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, UserDTO> {

	public UserController() {
		super(User.class, UserDTO.class);
	}

	@Autowired private UserService userService;
	
}
