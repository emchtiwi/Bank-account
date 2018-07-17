package com.bankaccount.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CurrentAccount")
@Data
@NoArgsConstructor
public class CurrentAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6735301525154465756L;
	private double overdraft;

	public CurrentAccount(Date creationDate, Customer customer, double overdraft) {
		super(creationDate, customer);
		this.overdraft = overdraft;
	}

}
