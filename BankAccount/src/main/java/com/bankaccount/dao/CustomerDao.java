package com.bankaccount.dao;

import com.bankaccount.entity.Customer;

public interface CustomerDao {

	Customer findByCustomerId(final Long id);

	Customer createCustomer(final Customer customer);

}
