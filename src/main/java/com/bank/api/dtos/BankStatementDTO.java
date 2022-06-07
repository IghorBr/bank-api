package com.bank.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BankStatementDTO extends BaseDTO {
	private static final long serialVersionUID = -6739508065487328408L;
	
	private Date createdAt;
	private String bankAction;
	private String destinyAccountNumber;
	private String destinyAgencyAgencyNumber;
	private String payerAccountNumber;
	private String payerAgencyAgencyNumber;
	private BigDecimal amount;

}
