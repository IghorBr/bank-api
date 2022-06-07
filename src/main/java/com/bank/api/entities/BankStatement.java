package com.bank.api.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.bank.api.domain.BaseDomain;
import com.bank.api.entities.enums.BankAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BankStatement extends BaseDomain {
	
	@PrePersist
	void prePersist() {
		this.createdAt = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statement_id")
	private Long id;
	
	private Date createdAt;
	
	@Enumerated(EnumType.STRING)
	private BankAction action;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "account_id")
	private Account destiny;
	
	@ManyToOne
	@JoinColumn(name = "payer_id", referencedColumnName = "account_id")
	private Account payer;
	
	private BigDecimal amount;
	
	private String information;

	public BankStatement(BankAction action, Account payer, Account destiny, BigDecimal amount, String information) {
		super();
		this.action = action;
		this.payer = payer;
		this.destiny = destiny;
		this.amount = amount;
		this.information = information;
	}

}