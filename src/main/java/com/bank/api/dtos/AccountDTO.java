package com.bank.api.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AccountDTO extends BaseDTO {
	private static final long serialVersionUID = -2451111199054370678L;
	
	@NotEmpty private String accountNumber;
	@NotEmpty private BigDecimal balance;
	private String agencyAgencyNumber;
	private Long userId;
	private List<BankStatementDTO> statements; 

}
