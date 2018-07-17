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
import com.bankaccount.entity.Deposit;
import com.bankaccount.entity.Operation;
import com.bankaccount.entity.Withdrawal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperationDaoTest {

	@Autowired
	OperationDao operationDao;

	@Autowired
	AccountDao accountDao;

	@Autowired
	CustomerDao customerDao;

	@Test
	public void a_findByOperationNumberShouldReturnOperation() throws Exception {

		/**
		 * Given
		 */
		Customer customerDelta = customerDao.createCustomer(new Customer("Michael", "DELTA", new Date()));
		Account currentAccount = accountDao.createAccount(new CurrentAccount(new Date(), customerDelta, 400L));

		Operation deposit = new Deposit(1000, new Date(), currentAccount);
		Operation withdrawal = new Withdrawal(500, new Date(), currentAccount);

		operationDao.createOperation(deposit);
		operationDao.createOperation(withdrawal);

		/**
		 * When
		 */
		Operation depositFound = operationDao.findByOperationNumber(1L);
		Operation withdrawalFound = operationDao.findByOperationNumber(2L);

		/**
		 * Then
		 */
		assertThat(depositFound.getNumber()).isEqualTo(1L);
		assertThat(depositFound.getAmount()).isEqualTo(deposit.getAmount());
		assertThat(depositFound.getOperationDate()).isEqualTo(deposit.getOperationDate());
		assertThat(depositFound.getAccount().getId()).isEqualTo(deposit.getAccount().getId());

		assertThat(withdrawalFound.getNumber()).isEqualTo(2L);
		assertThat(withdrawalFound.getAmount()).isEqualTo(withdrawal.getAmount());
		assertThat(withdrawalFound.getOperationDate()).isEqualTo(withdrawal.getOperationDate());
		assertThat(withdrawalFound.getAccount().getId()).isEqualTo(withdrawal.getAccount().getId());

	}

	@Test
	public void b_findByOperationNumberWhenNoOperationShouldReturnNull() throws Exception {

		/**
		 * When
		 */
		Operation depositFound = operationDao.findByOperationNumber(3L);
		Operation withdrawalFound = operationDao.findByOperationNumber(4L);

		/**
		 * Then
		 */
		assertThat(depositFound).isNull();
		assertThat(withdrawalFound).isNull();
	}

	@Test
	public void c_verifyOperationsNumber() throws Exception {

		/**
		 * When
		 */
		Account found = accountDao.findByAccountId(1L);

		/**
		 * Then
		 */
		assertThat(found.getOperations()).isNotEmpty().hasSize(2);

	}

}
