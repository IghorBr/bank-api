package com.bank.api.services.exceptions;

public class ObjectNotFoundException extends BankException {
	private static final long serialVersionUID = -4890531427042999568L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
