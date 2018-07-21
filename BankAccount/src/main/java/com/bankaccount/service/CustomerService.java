package com.bankaccount.service;

import com.bankaccount.dto.CustomerDto;
import com.bankaccount.exception.CustomerException;

public interface CustomerService {

	CustomerDto findByCustomerId(final Long id);

	CustomerDto createCustomer(final CustomerDto customerDto) throws CustomerException;

}
