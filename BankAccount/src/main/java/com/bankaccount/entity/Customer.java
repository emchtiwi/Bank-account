package com.bankaccount.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587854782220507166L;
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Account> accounts;

	public Customer(String firstName, String lastName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

}
