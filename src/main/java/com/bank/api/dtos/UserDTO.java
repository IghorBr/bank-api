package com.bank.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty private String name;
	@NotEmpty private String lastName;
	@NotEmpty private String middleName;
	@NotEmpty private String accountPassword;
	@NotEmpty private String internetPassword;
	@NotEmpty @CPF private String cpf;
	@NotEmpty @Email private String email;
}
