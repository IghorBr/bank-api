package com.bank.api.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 5736008414450457943L;
	
	private String email;
	private String authorities;
	private String token;
}
