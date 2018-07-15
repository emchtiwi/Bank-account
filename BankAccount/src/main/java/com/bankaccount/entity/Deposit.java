package com.bankaccount.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Deposit")
@NoArgsConstructor
public class Deposit extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8175655841793136415L;

	public Deposit(double amount, Date operationDate, Account account) {
		super(amount, operationDate, account);
	}

}
