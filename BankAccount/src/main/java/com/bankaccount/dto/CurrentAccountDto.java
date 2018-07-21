package com.bankaccount.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentAccountDto extends AccountDto {
	private double overdraft;

	public CurrentAccountDto(Date creationDate, CustomerDto customerDto, double overdraft) {
		super(creationDate, customerDto);
		this.overdraft = overdraft;
	}

}
