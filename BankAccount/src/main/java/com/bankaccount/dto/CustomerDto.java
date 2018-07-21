package com.bankaccount.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<AccountDto> accounts;

	public CustomerDto(String firstName, String lastName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.accounts = new ArrayList<>();
	}

}
