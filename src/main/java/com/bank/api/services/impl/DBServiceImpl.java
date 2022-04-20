package com.bank.api.services.impl;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.entities.Account;
import com.bank.api.entities.Agency;
import com.bank.api.entities.Manager;
import com.bank.api.entities.User;
import com.bank.api.services.AccountService;
import com.bank.api.services.AgencyService;
import com.bank.api.services.UserService;

@Service
public class DBServiceImpl {

	@Autowired private AccountService accountService;
	@Autowired private AgencyService agencyService;
	@Autowired private UserService userService;
	
	public void instantiateTestDatabase() {
//		User(@Email String email, @NotNull String name, @NotNull String lastName, String middleName,
//				@NotNull String accountPassword, @NotNull String internetPassword, @CPF @NotNull String cpf) 
		
		User user1 = new User("user1@email.com", "User 1", "Name", null, "1234", "123456", "781.248.020-84");
		User user2 = new User("user2@email.com", "User 2", "Name", "Silva", "abcd", "abcdef", "794.103.490-52");
		Manager manager = new Manager("manager@email.com", "Manager", "Name", null, "1ab2", "1a2b3c", "585.825.450-02");
		
		userService.saveAll(Arrays.asList(user1, user2, manager));
		
//		Agency(@NotNull Manager manager)
		
		Agency agency1 = new Agency(manager);
		
		agencyService.save(agency1);
		manager.setAgency(agency1);
		
		userService.save(manager);
		
//		Account(BigDecimal balance, @NotNull User user, @NotNull Agency agency)
		
		Account acc1 = new Account(new BigDecimal(1000), user1, agency1);
		Account acc2 = new Account(user2, agency1);
		
		accountService.saveAll(Arrays.asList(acc1, acc2));
		
		agency1.addAccount(acc1, acc2);
		agencyService.save(agency1);
		
		user1.setAccount(acc1);
		user2.setAccount(acc2);
		userService.saveAll(Arrays.asList(user1, user2));
		
	}
}
