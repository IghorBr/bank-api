package com.bank.api.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.bank.api.domain.BaseDomain;
import com.bank.api.services.utils.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Agency extends BaseDomain {

	@PrePersist
	private void prePersist() {
		this.agencyNumber = Util.getRandomString(5);
	}
	
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
	private List<Account> accounts = new ArrayList<>();
	
	public void addAccount(Account...accounts) {
		for (Account account : accounts) {
			this.accounts.add(account);
		}
	}

	public Agency(@NotNull Manager manager) {
		super();
		this.manager = manager;
	}
}
