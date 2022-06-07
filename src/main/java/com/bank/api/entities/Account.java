package com.bank.api.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.bank.api.domain.BaseDomain;
import com.bank.api.services.utils.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Account extends BaseDomain {
	
	@PrePersist
	private void prePersist() {
		this.createdAt = new Date();
		this.enabled = true;
		this.accountNumber = Util.getRandomString(7);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;
	
	@Column(unique = true, name = "account_number")
	private String accountNumber;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	private BigDecimal balance = new BigDecimal(0);
	
	@Column(name = "password")
	@NotNull
	private String password;
	
	private Boolean enabled;
	
	@OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
	private List<BankStatement> statements = new ArrayList<BankStatement>();
	
	@ManyToOne
	@JoinColumn(name = "agency_id", referencedColumnName = "agency_id")
	@NotNull
	private Agency agency;

	public Account(BigDecimal balance, @NotNull String password, @NotNull Agency agency) {
		super();
		this.balance = balance;
		this.password = password;
		this.agency = agency;
	}

	public Account(@NotNull String password, @NotNull Agency agency) {
		super();
		this.password = password;
		this.agency = agency;
	}
	
	public void addHistory(BankStatement bs) {
		this.statements.add(bs);
	}
}