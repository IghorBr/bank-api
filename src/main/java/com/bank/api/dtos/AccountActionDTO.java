package com.bank.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountActionDTO implements Serializable {
	private static final long serialVersionUID = 6619657265931527223L;
	
	@NotEmpty private String password;
	@NotNull private BigDecimal amount;

	//VALUES USED TO TRANSFER BETWEEN ACCOUNTS
	private String accountNumber;
	private String agencyTransferNumber;

}
