package com.bankaccount.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {

	private Long id;
	private Date creationDate;
	private double balance;
	private List<OperationDto> operations;
	private CustomerDto customer;

	public AccountDto(Date creationDate, CustomerDto customer) {
		super();
		this.creationDate = creationDate;
		this.operations = new ArrayList<>();
		this.customer = customer;
	}

}
