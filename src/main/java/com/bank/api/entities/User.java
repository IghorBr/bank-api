package com.bank.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.bank.api.domain.BaseDomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseDomain {
	
	@PrePersist
	private void prePersist() {
		createdAt = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(unique = true)
	@Email
	private String email;
	
	@NotNull
	private String name;
	
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "account_pass")
	@NotNull
	private String accountPassword;
	
	@Column(name = "internet_pass")
	@NotNull
	private String internetPassword;
	
	@CPF @NotNull
	private String cpf;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@OneToOne(mappedBy = "user")
	private Account account;

	public User(@Email String email, @NotNull String name, @NotNull String lastName, String middleName,
			@NotNull String accountPassword, @NotNull String internetPassword, @CPF @NotNull String cpf) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.middleName = middleName;
		this.accountPassword = accountPassword;
		this.internetPassword = internetPassword;
		this.cpf = cpf;
	}
}
