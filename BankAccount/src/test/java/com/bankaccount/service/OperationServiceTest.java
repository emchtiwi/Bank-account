package com.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccount.dao.AccountDao;
import com.bankaccount.dao.OperationDao;
import com.bankaccount.entity.Account;
import com.bankaccount.entity.CurrentAccount;
import com.bankaccount.entity.Customer;
import com.bankaccount.entity.Deposit;
import com.bankaccount.entity.Operation;
import com.bankaccount.entity.Withdrawal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OperationServiceTest {

	@Mock
	OperationDao operationDao;

	@Mock
	AccountDao accountDao;

	@InjectMocks
	OperationServiceImpl operationServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void a_DepositInAnExistingAccount() throws Exception {

		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(new CurrentAccount());
		when(operationDao.createOperation(any(Operation.class))).thenReturn(new Deposit());
		when(accountDao.createAccount(any(Account.class))).thenReturn(new CurrentAccount());

		/**
		 * When
		 */
		AccountDto accountDto = operationServiceImpl.deposit(1L, 1000.0);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		verify(operationDao, times(1)).createOperation(any(Operation.class));
		verify(accountDao, times(1)).createAccount(any(Account.class));
		assertThat(accountDto).isNotNull();

	}

	@Test
	public void b_DepositInAnNonExistingAccount() throws Exception {

		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(null);

		/**
		 * When
		 */
		AccountDto accountDto = operationServiceImpl.deposit(2L, 1000.0);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		verify(operationDao, times(0)).createOperation(any(Operation.class));
		verify(accountDao, times(0)).createAccount(any(Account.class));
		assertThat(accountDto).isNull();

	}

	@Test
	public void c_withdrawalFromAnExistingAccountAvailableFunds() throws Exception {
		
		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(new CurrentAccount(new Date(), new Customer(), 400));
		when(operationDao.createOperation(any(Operation.class))).thenReturn(new Withdrawal());
		when(accountDao.createAccount(any(Account.class))).thenReturn(new CurrentAccount());

		/**
		 * When
		 */
		AccountDto accountDto = operationServiceImpl.withdrawal(1L, 300);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		verify(operationDao, times(1)).createOperation(any(Operation.class));
		verify(accountDao, times(1)).createAccount(any(Account.class));
		assertThat(accountDto).isNotNull();
	}

	@Test
	public void d_withdrawalFromAnExistingAccountUnavailableFunds() throws Exception {

		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(new CurrentAccount(new Date(), new Customer(), 400));

		/*
		 * Given
		 */
		AccountDto accountDto = operationServiceImpl.withdrawal(1L, 500);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		verify(operationDao, times(0)).createOperation(any(Operation.class));
		verify(accountDao, times(0)).createAccount(any(Account.class));
		assertThat(accountDto).isNotNull();

	}

	@Test
	public void e_withdrawalFromAnNonExistingAccount() throws Exception {
		
		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(null);

		/**
		 * When
		 */
		AccountDto accountDto = operationServiceImpl.withdrawal(1L, 1000.0);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		verify(operationDao, times(0)).createOperation(any(Operation.class));
		verify(accountDao, times(0)).createAccount(any(Account.class));
		assertThat(accountDto).isNull();
	}

	public void f_showAllOperations() throws Exception {

		/*
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(new CurrentAccount());

		/**
		 * When
		 */
		List<OperationDto> operations = operationServiceImpl.showAllOperations(1L);

		/**
		 * Then
		 */
		verify(operations).isEmpty();
	}
}
