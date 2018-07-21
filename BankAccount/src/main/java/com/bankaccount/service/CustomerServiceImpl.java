package com.bankaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.dao.CustomerDao;
import com.bankaccount.dto.CustomerDto;
import com.bankaccount.entity.Customer;
import com.bankaccount.exception.CustomerException;
import com.bankaccount.service.mapper.ModelMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Method to get customer by his id
	 */
	@Override
	public CustomerDto findByCustomerId(final Long id) {
		Customer customer = customerDao.findByCustomerId(id);
		if (customer == null) {
			return null;
		}
		return modelMapper.map(customer, CustomerDto.class);
	}

	/**
	 * Method to create a new customer
	 * 
	 * @throws CustomerException
	 */
	@Override
	public CustomerDto createCustomer(final CustomerDto customerDto) throws CustomerException {
		if (customerDto == null) {
			throw new CustomerException("You could not create a null customer");
		}
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customer = customerDao.createCustomer(customer);
		return modelMapper.map(customer, CustomerDto.class);
	}

}
