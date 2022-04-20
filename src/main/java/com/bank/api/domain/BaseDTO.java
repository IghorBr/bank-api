package com.bank.api.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Long id;
}
