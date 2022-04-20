package com.bank.api.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Manager extends User {

	@OneToOne
	@JoinColumn(name = "agency_id", referencedColumnName = "agency_id")
	private Agency agency;
}
