package com.bankaccount.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bankaccount.entity.Account;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

	@PersistenceContext
	EntityManager em;

	/**
	 * Method to get account by its id
	 */
	@Override
	public Account findByAccountId(final Long id) {
		return em.find(Account.class, id);
	}

	/**
	 * Method to create a new account
	 */
	@Override
	public Account createAccount(final Account account) {
		Account saved = em.merge(account);
		em.flush();
		return saved;
	}

}