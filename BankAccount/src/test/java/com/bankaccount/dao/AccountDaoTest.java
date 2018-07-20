package com.bankaccount.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccount.entity.Account;
import com.bankaccount.entity.CurrentAccount;
import com.bankaccount.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDaoTest {

	@Autowired
	AccountDao accountDao;

	@Autowired
	CustomerDao customerDao;

	@Test
	public void a_findByAccountIdShouldReturnAccount() throws Exception {

		/**
		 * Given
		 */
		Customer customerDelta = customerDao.createCustomer(new Customer("Michael", "DELTA", new Date()));
		Account currentAccount = new CurrentAccount(new Date(), customerDelta, 1000L);
		accountDao.createAccount(currentAccount);

		/**
		 * When
		 */
		Account found = accountDao.findByAccountId(1L);

		/**
		 * Then
		 */
		assertThat(found.getId()).isEqualTo(1L);
		assertThat(found.getCreationDate()).isEqualTo(currentAccount.getCreationDate());
		assertThat(found.getBalance()).isEqualTo(currentAccount.getBalance());
		assertThat(found.getCustomer().getId()).isEqualTo(currentAccount.getCustomer().getId());
		assertThat(found.getOperations()).isEmpty();
	}

	@Test
	public void b_findByAccountIdWhenNoAccountShouldReturnNull() throws Exception {

		/**
		 * When
		 */
		Account found = accountDao.findByAccountId(2L);

		/**
		 * Then
		 */
		assertThat(found).isNull();
	}

}
