package com.bank.api.dtos;

import java.math.BigDecimal;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AccountDTO extends BaseDTO {
	private static final long serialVersionUID = -2451111199054370678L;
	
	private String accountNumber;
	private BigDecimal balance;

}
