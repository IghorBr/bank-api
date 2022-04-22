package com.bank.api.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = -4589044799120800679L;
	
	private String email;
	private String password;
	
}
