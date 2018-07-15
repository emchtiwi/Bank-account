package com.bankaccount.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Withdrawal")
@NoArgsConstructor
public class Withdrawal extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5485744787836670108L;

	public Withdrawal(double amount, Date operationDate, Account account) {
		super(amount, operationDate, account);
	}

}
