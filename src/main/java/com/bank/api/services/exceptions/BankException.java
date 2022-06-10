package com.bank.api.services.exceptions;

public class BankException extends RuntimeException {
	private static final long serialVersionUID = -6146110587788048338L;
	
	public BankException(String msg) {
		super(msg);
	}
	
	public BankException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
