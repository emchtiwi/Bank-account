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

import com.bankaccount.dao.CustomerDao;
import com.bankaccount.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest {

	@Mock
	CustomerDao customerDao;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void a_findByCustomerIdShouldReturnCustomer() throws Exception {

		/**
		 * Given
		 */
		when(customerDao.findByCustomerId(anyLong())).thenReturn(new Customer());

		/**
		 * When
		 */
		CustomerDto found = customerServiceImpl.findByCustomerId(1L);

		/**
		 * Then
		 */
		verify(customerDao, times(1)).findByCustomerId(anyLong());
		assertThat(found).isNotNull();
	}

	@Test
	public void b_findByCustomerIdShouldReturnNull() throws Exception {

		/**
		 * Given
		 */
		when(customerDao.findByCustomerId(anyLong())).thenReturn(null);

		/*
		 * When
		 */
		Customer found = customerDao.findByCustomerId(2L);

		/*
		 * Then
		 */
		verify(customerDao, times(1)).findByCustomerId(anyLong());
		assertThat(found).isNull();

	}

	@Test
	public void c_addCustomerShouldReturnNewCustomer() throws Exception {

		/*
		 * Given
		 */
		when(customerDao.createCustomer(any(Customer.class))).thenReturn(new Customer());

		/*
		 * When
		 */
		CustomerDto customerDto = customerServiceImpl.createCustomer(new CustomerDto());

		/**
		 * Then
		 */
		verify(customerDao, times(1)).createCustomer(any(Customer.class));
		assertThat(customerDto).isNotNull();

	}

	@Test
	public void d_addNullCustomer() throws Exception {

		/*
		 * Given
		 */
		when(customerDao.createCustomer(any(Customer.class))).thenReturn(null);

		/*
		 * When
		 */
		customerServiceImpl.createCustomer(null);

		/**
		 * Then
		 */
		verify(customerDao, times(0)).createCustomer(any(Customer.class));

	}

}
