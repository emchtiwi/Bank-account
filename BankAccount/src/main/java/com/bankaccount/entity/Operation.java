package com.bankaccount.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OPERATION_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4721359354983096020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	private double amount;
	private Date operationDate;
	@ManyToOne
	@JoinColumn(name = "account")
	private Account account;

	public Operation(double amount, Date operationDate, Account account) {
		super();
		this.amount = amount;
		this.operationDate = operationDate;
		this.account = account;
	}

}
