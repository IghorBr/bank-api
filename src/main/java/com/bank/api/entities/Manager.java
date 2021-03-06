package com.bank.api.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.bank.api.entities.enums.UserType;

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

	public Manager(@Email String email, @NotNull String name, @NotNull String lastName, String middleName,
			@NotNull String password, @CPF @NotNull String cpf, @NotNull UserType userType) {
		super(email, name, lastName, middleName, password, cpf, userType);
	}
}
