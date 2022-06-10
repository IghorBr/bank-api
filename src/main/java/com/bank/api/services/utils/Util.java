package com.bank.api.services.utils;

import java.util.Random;

import com.bank.api.services.exceptions.BankException;

public class Util {
	
	private Util() {
		throw new BankException("This is a utility class and cannot be instantiated");
	}
	
	private static final Random random = new Random();
	
	public static String getRandomString(int n) {
		String numericString = "0123456789";
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n+1; i++) {
			if (i == n-1) {
				sb.append("-");
				continue;
			}
			
			int index = (numericString.length() * Util.random.nextInt());
			sb.append(numericString.charAt(index));
		}
		
		return sb.toString();
	}
}
