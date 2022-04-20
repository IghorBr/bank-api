package com.bank.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Agency;
import com.bank.api.repositories.AgencyRepository;
import com.bank.api.services.AgencyService;
import com.querydsl.core.types.Predicate;

@Service
public class AgencyServiceImpl extends BaseServiceImpl<Agency> implements AgencyService  {

	@Autowired private AgencyRepository agencyRepository;

	@Override
	public List<Agency> search(Predicate predicate) {
		List<Agency> agencies = new ArrayList<Agency>();
		
		agencyRepository.findAll(predicate).forEach(agencies::add);
		return agencies;
	}
}
