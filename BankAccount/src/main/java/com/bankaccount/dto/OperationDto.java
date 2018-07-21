package com.bankaccount.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

	private Long number;
	private double amount;
	private Date operationDate;
	private AccountDto account;

	public OperationDto(double amount, Date operationDate, AccountDto account) {
		super();
		this.amount = amount;
		this.operationDate = operationDate;
		this.account = account;
	}

}
