package com.bank.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AgencyDTO;
import com.bank.api.entities.Agency;

@RestController
@RequestMapping(value = "/agencies")
public class AgencyController extends BaseController<Agency, AgencyDTO> {

	public AgencyController() {
		super(Agency.class, AgencyDTO.class);
	}

}
