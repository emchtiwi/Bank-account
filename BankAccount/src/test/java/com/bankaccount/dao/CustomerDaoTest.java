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

import com.bankaccount.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoTest {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void a_findByCustomerIdShouldReturnCustomer() throws Exception {

		/*
		 * Given
		 */
		Customer customerDelta = new Customer("Michael", "DELTA", new Date());
		customerDao.createCustomer(customerDelta);

		/*
		 * When
		 */
		Customer found = customerDao.findByCustomerId(1L);

		/*
		 * Then
		 */
		assertThat(found.getId()).isEqualTo(1L);
		assertThat(found.getFirstName()).isEqualTo(customerDelta.getFirstName());
		assertThat(found.getLastName()).isEqualTo(customerDelta.getLastName());
		assertThat(found.getBirthDate()).isEqualTo(customerDelta.getBirthDate());

	}

	@Test
	public void b_findByCustomerIdWhenNoCustomerShouldReturnNull() throws Exception {

		/*
		 * When
		 */
		Customer found = customerDao.findByCustomerId(2L);

		/*
		 * Then
		 */
		assertThat(found).isNull();

	}

}
