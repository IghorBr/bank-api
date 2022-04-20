package com.bank.api.services.utils;

public class Util {

	public static String getRandomString(int n) {
		String numericString = "0123456789";
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n+1; i++) {
			if (i == n-1) {
				sb.append("-");
				continue;
			}
			
			int index = (int) (numericString.length() * Math.random());
			sb.append(numericString.charAt(index));
		}
		
		return sb.toString();
	}
}
