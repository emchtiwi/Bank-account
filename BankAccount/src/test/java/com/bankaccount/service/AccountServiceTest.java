package com.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankaccount.dao.AccountDao;
import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.CurrentAccountDto;
import com.bankaccount.entity.Account;
import com.bankaccount.entity.CurrentAccount;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {

	@Mock
	AccountDao accountDao;

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void a_findByAccountIdShouldReturnAccount() throws Exception {

		/**
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(new CurrentAccount());

		/**
		 * When
		 */
		AccountDto found = accountServiceImpl.findByAccountId(1L);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		assertThat(found).isNotNull();
	}

	@Test
	public void b_findByAccountIdShouldReturnNull() throws Exception {

		/**
		 * Given
		 */
		when(accountDao.findByAccountId(anyLong())).thenReturn(null);

		/**
		 * When
		 */
		AccountDto found = accountServiceImpl.findByAccountId(2L);

		/**
		 * Then
		 */
		verify(accountDao, times(1)).findByAccountId(anyLong());
		assertThat(found).isNull();
	}

	@Test
	public void c_addAccount() throws Exception {

		/*
		 * Given
		 */
		when(accountDao.createAccount(any(Account.class))).thenReturn(new CurrentAccount());

		/*
		 * When
		 */
		accountServiceImpl.createAccount(new CurrentAccountDto());

		/**
		 * Then
		 */
		verify(accountDao, times(1)).createAccount(any(Account.class));

	}

	@Test
	public void d_addNullAccount() throws Exception {

		/**
		 * Given
		 */
		when(accountDao.createAccount(any(Account.class))).thenReturn(null);

		/**
		 * When
		 */
		accountServiceImpl.createAccount(null);

		/**
		 * Then
		 */
		verify(accountDao, times(0)).createAccount(any(Account.class));

	}

}
