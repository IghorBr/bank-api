package com.bank.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.UserDTO;
import com.bank.api.entities.Manager;
import com.bank.api.repositories.AgencyRepository;
import com.bank.api.services.ManagerService;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController extends BaseController<Manager, UserDTO> {

	public ManagerController() {
		super(Manager.class, UserDTO.class);
	}
	
	@Autowired private ManagerService managerService;
	
	public ResponseEntity<List<UserDTO>> search(
		@QuerydslPredicate(root = Manager.class, bindings = AgencyRepository.class) Predicate predicate) {
		
		List<UserDTO> dtos = mapList(managerService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
}
