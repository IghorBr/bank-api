package com.bank.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.UserCreateDTO;
import com.bank.api.dtos.UserDTO;
import com.bank.api.entities.Manager;
import com.bank.api.entities.User;
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
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<UserDTO>> search(
		@QuerydslPredicate(root = Manager.class, bindings = AgencyRepository.class) Predicate predicate) {
		
		List<UserDTO> dtos = mapList(managerService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
	
	@PostMapping(value = "/new-acc")
	public ResponseEntity<Void> createAccount(@RequestBody @Valid UserCreateDTO userCreate) throws Exception {
		User user = managerService.createAccount(userCreate);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
