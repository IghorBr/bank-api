package com.bank.api.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bank.api.domain.BaseDomain;

import lombok.NoArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Agency extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agency_id")
	private Long id;
	
	@Column(name = "agency_number")
	private String agencyNumber;
	
	@OneToOne(mappedBy = "agency")
	@NotNull
	private Manager manager;
	
	@OneToMany(mappedBy = "agency")
	private Set<Account> accounts;
}
