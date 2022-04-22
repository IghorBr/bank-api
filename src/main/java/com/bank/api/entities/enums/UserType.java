package com.bank.api.entities.enums;

import lombok.Getter;

@Getter
public enum UserType {
	
	USER("ROLE_USER"),
	MANAGER("ROLE_MANAGER"),
	ADMIN("ROLE_ADMIN");
	
	private String description;
	
	private UserType(String description) {
		this.description = description;
	}

}
