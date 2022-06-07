package com.bank.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AccountDTO;
import com.bank.api.dtos.UserDTO;
import com.bank.api.entities.Account;
import com.bank.api.entities.User;
import com.bank.api.repositories.UserRepository;
import com.bank.api.services.UserService;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User, UserDTO> {

	public UserController() {
		super(User.class, UserDTO.class);
	}

	@Autowired private UserService userService;
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<UserDTO>> search(
		@QuerydslPredicate(root = User.class, bindings = UserRepository.class) Predicate predicate) {
		
		List<UserDTO> dtos = mapList(userService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value="/email")
	public ResponseEntity<UserDTO> findUserByEmail(@RequestParam(required = true, name = "email") String email) {
		UserDTO dto = mapper.map(userService.findByEmail(email), UserDTO.class);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value="/account/{id}")
	public ResponseEntity<AccountDTO> findAccountByUserId(@PathVariable Long id) {
		Account account = userService.findAccountByUserId(id);
		AccountDTO dto = mapper.map(account, AccountDTO.class);
		
		return ResponseEntity.ok(dto);
	}
}
