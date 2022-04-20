package com.bank.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.domain.BaseServiceImpl;
import com.bank.api.entities.Agency;
import com.bank.api.repositories.AgencyRepository;
import com.bank.api.services.AgencyService;

@Service
public class AgencyServiceImpl extends BaseServiceImpl<Agency> implements AgencyService  {

	@Autowired private AgencyRepository agencyRepository;
}
