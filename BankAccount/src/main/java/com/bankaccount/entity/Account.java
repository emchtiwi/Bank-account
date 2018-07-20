package com.bankaccount.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -265342183852147454L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Date creationDate;
	private double balance;
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Operation> operations;
	@ManyToOne()
	@JoinColumn(name = "customer")
	private Customer customer;

	public Account(Date creationDate, Customer customer) {
		super();
		this.creationDate = creationDate;
		this.balance = 0L;
		this.operations = new ArrayList<>();
		this.customer = customer;
	}

}
