package com.bank.api.services.exceptions;

public class AmountValueException extends BankException {
	private static final long serialVersionUID = 1935499864942473125L;
	
	public AmountValueException(String msg) {
		super(msg);
	}
	
	public AmountValueException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
