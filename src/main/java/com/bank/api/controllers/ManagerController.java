package com.bank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.UserDTO;
import com.bank.api.entities.Manager;
import com.bank.api.services.ManagerService;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController extends BaseController<Manager, UserDTO> {

	public ManagerController() {
		super(Manager.class, UserDTO.class);
	}
	
	@Autowired private ManagerService managerService;
}
