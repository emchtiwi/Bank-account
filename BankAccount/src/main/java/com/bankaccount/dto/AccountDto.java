package com.bankaccount.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {

	private Long id;
	private Date creationDate;
	private double balance;
	private String accountType;
	private CustomerDto customer;

	public AccountDto(Date creationDate, CustomerDto customer) {
		super();
		this.creationDate = creationDate;
		this.customer = customer;
	}

}
