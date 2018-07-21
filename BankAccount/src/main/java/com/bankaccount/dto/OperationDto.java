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
	private String operationType;

	public OperationDto(double amount, Date operationDate) {
		super();
		this.amount = amount;
		this.operationDate = operationDate;
	}

}
