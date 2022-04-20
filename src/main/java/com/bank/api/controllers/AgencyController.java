package com.bank.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.domain.BaseController;
import com.bank.api.dtos.AgencyDTO;
import com.bank.api.entities.Agency;
import com.bank.api.repositories.AgencyRepository;
import com.bank.api.services.AgencyService;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping(value = "/agencies")
public class AgencyController extends BaseController<Agency, AgencyDTO> {

	public AgencyController() {
		super(Agency.class, AgencyDTO.class);
	}

	@Autowired private AgencyService agencyService;
	
	public ResponseEntity<List<AgencyDTO>> search(
		@QuerydslPredicate(root = Agency.class, bindings = AgencyRepository.class) Predicate predicate) {
		
		List<AgencyDTO> dtos = mapList(agencyService.search(predicate));
		return ResponseEntity.ok().body(dtos);
	}
}
