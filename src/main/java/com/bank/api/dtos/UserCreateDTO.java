package com.bank.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO implements Serializable {
	private static final long serialVersionUID = -2534191575835743835L;
	
	@NotEmpty @Email private String email;
	@NotEmpty private String name;
	@NotEmpty private String lastName;
	@NotEmpty @Length(min = 6, max = 6) private String password;
	@NotEmpty @CPF private String cpf;
	private String middleName;
	
	private BigDecimal balance;
	@NotEmpty @Length(min = 4, max = 4) private String passwordAccount;
}
