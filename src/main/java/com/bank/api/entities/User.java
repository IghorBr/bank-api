package com.bank.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.bank.api.domain.BaseDomain;
import com.bank.api.entities.enums.UserType;

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
		this.enabled = true;
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
	
	@Column(name = "password")
	@NotNull
	private String password;
	
	@CPF @NotNull
	private String cpf;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private UserType userType;
	
	private Boolean enabled;

	public User(@Email String email, @NotNull String name, @NotNull String lastName, String middleName,
			@NotNull String password, @CPF @NotNull String cpf, @NotNull UserType userType) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.middleName = middleName;
		this.password = password;
		this.cpf = cpf;
		this.userType = userType;
	}
	
	
	
	
}
