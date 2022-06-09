package com.bank.api.dtos;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lastName;
	private String middleName;
	private String email;
	private String cpf;
	private String userType;
	private AccountDTO account;
}
