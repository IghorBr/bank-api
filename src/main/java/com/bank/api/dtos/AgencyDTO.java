package com.bank.api.dtos;

import com.bank.api.domain.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AgencyDTO extends BaseDTO {
	private static final long serialVersionUID = -2649485229582952136L;
	
	private String agencyNumber;
}
