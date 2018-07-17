package com.bankaccount.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bankaccount.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method to get customer by id
	 */
	@Override
	public Customer findByCustomerId(final Long id) {
		return em.find(Customer.class, id);
	}

	/**
	 * Method to create an new customer
	 */
	@Override
	public Customer createCustomer(final Customer customer) {
		return em.merge(customer);
	}

}
