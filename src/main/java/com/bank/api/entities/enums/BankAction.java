package com.bank.api.entities.enums;

import lombok.Getter;

@Getter
public enum BankAction {

	CREATE("Create"),
	DEPOSIT("Deposit"),
	WITHDRAW("Withdraw"),
	TRANSFER("Transfer");
	
	private String description;
	
	private BankAction(String description) {
		this.description = description;
	}
}
