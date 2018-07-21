package com.bankaccount.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.CustomerDto;
import com.bankaccount.dto.OperationDto;
import com.bankaccount.service.OperationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountControllerTest {

	
	MockMvc mockMvc;

	@Mock
	OperationService operationService;

	@InjectMocks
	BankAccountController bankAccountController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
	}

	@Test
	public void a_showAllOperations() throws Exception {

		List<OperationDto> operations = Arrays.asList(
				new OperationDto(10, new Date()),
				new OperationDto(20, new Date()), 
				new OperationDto(30, new Date()));
		when(operationService.showAllOperations(anyLong())).thenReturn(operations);
		mockMvc.perform(get("/bankaccount/showAllOperations/1")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].amount", is(10.0)));
		verify(operationService, times(1)).showAllOperations(anyLong());
		verifyNoMoreInteractions(operationService);

	}

	@Test
	public void b_deposit() throws Exception {
		AccountDto account = new AccountDto(new Date(), new CustomerDto("Michael", "DELTA", new Date()));
		when(operationService.deposit(anyLong(), anyDouble())).thenReturn(account);
		mockMvc.perform(post("/bankaccount/deposit?accountId=1&amount=1")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.customer.firstName", is("Michael")))
				.andExpect(jsonPath("$.customer.lastName", is("DELTA")))
				.andExpect(jsonPath("$.balance", is(0.0)));
		verify(operationService, times(1)).deposit(anyLong(), anyDouble());
		verifyNoMoreInteractions(operationService);
	}

	@Test
	public void c_withdrawal() throws Exception {
		AccountDto account = new AccountDto(new Date(), new CustomerDto("Laurent", "ALPHA", new Date()));
		when(operationService.withdrawal(anyLong(), anyDouble())).thenReturn(account);
		mockMvc.perform(post("/bankaccount/withdrawal?accountId=1&amount=1")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.customer.firstName", is("Laurent")))
				.andExpect(jsonPath("$.customer.lastName", is("ALPHA")))
				.andExpect(jsonPath("$.balance", is(0.0)));
		verify(operationService, times(1)).withdrawal(anyLong(), anyDouble());
		verifyNoMoreInteractions(operationService);
	}
}