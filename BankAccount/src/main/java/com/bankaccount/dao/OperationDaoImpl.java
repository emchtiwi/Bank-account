package com.bankaccount.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bankaccount.entity.Operation;

@Repository
@Transactional
public class OperationDaoImpl implements OperationDao {

	@PersistenceContext
	EntityManager em;

	/*
	 * Method to get operation by its number
	 */
	@Override
	public Operation findByOperationNumber(final Long number) {
		return em.find(Operation.class, number);
	}

	/*
	 * Method to create a new operation
	 */
	@Override
	public Operation createOperation(final Operation operation) {
		Operation saved = em.merge(operation);
		em.flush();
		return saved;
	}

}
